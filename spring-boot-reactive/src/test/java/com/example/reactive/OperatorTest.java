package com.example.reactive;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class OperatorTest {
    @Test
    public void flatMapOperator(){
        Flux<Integer> source = Flux.just(1, 2, 3);
        Flux<String> result = source.
                flatMap(x -> Flux.just("A" + x, "B" + x));
        result.subscribe(System.out::println);
    }

    @Test
    public void flatMapSequential(){
        Flux<Integer> source = Flux.just(1, 2, 3);
        Flux<String> result = source.flatMapSequential(x -> Flux.just("A" + x, "B" + x));
        result.subscribe(System.out::println);
    }

    @Test
    public void concatMap(){
        Flux<Integer> source = Flux.just(1, 2, 3);
        Flux<String> result = source.
                concatMap(x -> Flux.just("A" + x, "B" + x));
        result.subscribe(System.out::println);
    }

    @Test
    public void mergeAndConcat(){
        Flux<Integer> flux1 = Flux.just(1, 2, 3);
        Flux<Integer> flux2 = Flux.just(4, 5, 6);
        Flux<Integer> mergedFlux = Flux.merge(flux1, flux2); // interleaved
        Flux<Integer> concatenatedFlux = Flux.concat(flux1, flux2); // in order
        System.out.println("merge: ");
        mergedFlux.subscribe(System.out::println);
        System.out.println("concat: ");
        concatenatedFlux.subscribe(System.out::println);
    }

    @Test
    public void zipOperator(){
        Flux<Integer> numbers = Flux.just(1, 2, 3);
        Flux<String> letters = Flux.just("A", "B", "C");
        Flux<String> combined = Flux.zip(numbers, letters, (number, letter) -> number + letter);
        combined.subscribe(System.out::println);
    }

    @Test
    public void chainingOperator(){
        Flux<Integer> numbers = Flux.just(1, 2, 3, 4, 5);
        Flux<String> transformedFlux = numbers
                .filter(number -> number % 2 == 0)
                .map(number -> "Even: " + number);
        transformedFlux.subscribe(System.out::println);
    }

    @Test
    public void chainingMapAndFilter(){
        // Chaining map, filter, and reduce

        Flux<Integer> numbers = Flux.range(1, 5);

        Mono<Integer> sumOfEvenSquaredNumbers = numbers
                .filter(number -> number % 2 == 0) // Filter even numbers
                .map(evenNumber -> evenNumber * evenNumber) // Square the even numbers
                .reduce(0, (acc, squaredNumber) -> acc + squaredNumber); // Calculate sum

        sumOfEvenSquaredNumbers.subscribe(result -> System.out.println("Sum: " + result));

    }

    @Test
    void testSwitchIfEmptyOperator() {
        Flux<Object> switched = Flux.empty()
                .switchIfEmpty(Mono.just("switched"))
                .log();
        switched.subscribe(System.out::println);
    }

    @Test
    void testDefaultIfEmptyOperator() {
        Flux<Object> emptyFlux = Flux.empty()
                .defaultIfEmpty("default")
                .log();
        emptyFlux.subscribe(System.out::println);
    }
}
