package com.example.reactive;

import com.example.reactive.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

@Slf4j
public class ErrorHandlingTest {

    @Test
    public void doOnErrorTest(){
        Flux<Integer> numbers = Flux.just(1, 2, 3, 4, 5)
                .concatWith(Flux.error(new RuntimeException("Oops! An error occurred.")))
                .map(number -> 10 / (number - 3)) // This will cause an ArithmeticException

                .doOnError(throwable -> System.err.println("Error occurred: " + throwable.getMessage()))

                .onErrorReturn(-1); // Provide a fallback value in case of an error


        numbers.subscribe(
                value -> System.out.println("Received: " + value),
                error -> System.err.println("Subscriber error: " + error.getMessage())
        );
    }

    @Test
    public void onErrorReturnTest(){
        Flux<Integer> numbers = Flux.just(1, 2, 3, 4, 0, 21)
                .map(x -> 100/x)
                .onErrorReturn(Integer.MAX_VALUE);

        numbers.subscribe(
                value -> log.info("Received: {}", value),
                error -> log.error("A number wasn't a proper int: ", error)
        );
    }

    @Test
    public void onErrorResumeTest(){
        Flux<Integer> numbers = Flux.just(1, 2, 3, 4, 0, 21)
                .map(x -> 100/x)
                .onErrorResume(ex -> Flux.range(1,3));

        numbers.subscribe(
                value -> log.info("Received: {}", value),
                error -> log.error("A number wasn't a proper int: ", error)
        );
    }

    @Test
    public void onErrorMap(){
        Flux<Integer> numbers = Flux.just(1, 2, 3, 4, 0, 21)
                .map(x -> 100/x)
                .onErrorMap(ex -> new BusinessException("We messed up somewhere"));

        numbers.subscribe(
                value -> log.info("Received: {}", value),
                error -> log.error("A number wasn't a proper int: ", error)
        );
    }

}
