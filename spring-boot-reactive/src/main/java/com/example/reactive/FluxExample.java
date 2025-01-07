package com.example.reactive;

import reactor.core.publisher.Flux;

public class FluxExample {
    public static void main(String [] args){
        Flux<Integer> flux = Flux.range(1, 5);

        flux.subscribe(
                value -> System.out.println("Received: " + value),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );
    }
}
