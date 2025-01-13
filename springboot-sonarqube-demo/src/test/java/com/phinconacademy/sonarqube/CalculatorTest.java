package com.phinconacademy.sonarqube;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@Slf4j
class CalculatorTest {
    Calculator calculator;

    @BeforeEach
    void setup(){
        log.info(" inside before each ");
        this.calculator = new Calculator();
    }

    @Test
    @DisplayName(value = "This test should return equals when add two number")
    void should_returnEquals_when_addTwoNumber(){
        // given
        int firstNumber = 10;
        int secondNumber = 20;
        int expected = 30;

        // when
        int actual = calculator.add(firstNumber, secondNumber);

        // then
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "1st={0}, 2nd={1}, expected={2}")
    @CsvSource(value = {"-10, -1, 10", "-10, -20, 200", "-3, -40, 120"})
    void should_returnTrue_when_multiplyTwoNegativeNumbers(int givenFirstNumber, int givenSecondNumber, int expected){
        // when
        int actual = calculator.multiply(givenFirstNumber, givenSecondNumber);

        // then
        Assertions.assertEquals(actual, expected);
    }

    @ParameterizedTest(name = "1st={0}, 2nd={1}, expected={2}")
    @CsvSource(value = {"100, 10, 10", "50, 5, 10", "1000, 20, 50"})
    void should_returnTrue_when_divideTwoNumbers(int givenFirstNumber, int givenSecondNumber, int expected){
        // when
        int actual = calculator.divide(givenFirstNumber, givenSecondNumber);

        // then
        Assertions.assertEquals(actual, expected);
    }
}
