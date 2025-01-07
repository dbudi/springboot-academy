package com.example.reactive;

import reactor.core.publisher.Flux;

public class MapOperatorExample {
    public static void main (String [] args){
        Flux<Integer> originalFlux = Flux.just(1, 2, 3);
        Flux<String> mappedFlux = originalFlux.map(number -> "Number: " + number);
        mappedFlux.subscribe(
                value -> System.out.println("mapped : " + value),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );
    }
}
