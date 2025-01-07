package com.example.reactive;

import reactor.core.publisher.Flux;

public class FlatMapOperatorExample {
    public static void main(String[] args){
        Flux<Integer> numbers = Flux.just(1, 2, 3);
        Flux<String> letterFlux = numbers
                .flatMap(number -> Flux.just("A", "B")
                        .map(letter -> number + letter));
        letterFlux.subscribe(
                value -> System.out.println("flatmapFlux : " + value),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );
    }
}
