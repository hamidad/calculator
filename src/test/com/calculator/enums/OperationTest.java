package com.calculator.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperationTest {

    @Test
    @DisplayName("Addition tests")
    void add() {
        assertEquals(2.5, Operation.add(1.0, 1.5));
    }

    @Test
    @DisplayName("Subtraction tests")
    void subtract() {
       assertEquals(-0.1, Operation.subtract(2.0, 2.1));
    }

    @Test
    @DisplayName("Multiplication tests")
    void multiply() {
        assertEquals(1.5, Operation.multiply(1.0, 1.5));
    }

    @Test
    @DisplayName("Division tests")
    void divide() {
        assertEquals(0.75, Operation.divide(1.5, 2.0));
    }
}
