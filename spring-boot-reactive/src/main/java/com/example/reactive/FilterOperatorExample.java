package com.example.reactive;

import reactor.core.publisher.Flux;

public class FilterOperatorExample {
    public static void main(String [] args){
        Flux<Integer> numbers = Flux.range(1, 10);
        Flux<Integer> evenNumbers = numbers.filter(number -> number % 2 == 0);

        evenNumbers.subscribe(
                value -> System.out.println("filtered : " + value),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );
    }
}
