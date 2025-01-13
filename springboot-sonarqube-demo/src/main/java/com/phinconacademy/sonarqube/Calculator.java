package com.phinconacademy.sonarqube;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Calculator {
    int add(int a, int b){
        log.info("add a={}, b={}", a, b);
        return a + b;
    }

    int multiply(int a, int b){
        log.info("multiply a={}, b={}", a, b);
        return a * b;
    }

    int divide(int a, int b){
        log.info("divide a={}, b={}", a, b);
        return a / b;
    }
}
