package com.example.reactive;

import reactor.core.publisher.Mono;

public class MonoExample {
    public static void main(String[] args){
        Mono<Integer> mono1 = Mono.just(26);
        mono1.subscribe(value -> System.out.println(value)); // 26

        Mono<String> mono2 = Mono.just("Spring WebFlux");
        mono2.subscribe(value -> System.out.println(value)); // Spring WebFlux
    }
}
