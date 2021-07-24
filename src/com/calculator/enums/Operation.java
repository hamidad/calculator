package com.calculator.enums;

import com.calculator.app_constants.AppConstants;
import com.calculator.app_utils.AppUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Operation {
    CLEAR_ENTRY("CE"),
    CLEAR("C"),
    CLEAR_PREV_NUM("←"),
    DIVISION("÷"),
    MULTIPLICATION("×"),
    SUBTRACTION("–"),
    ADDITION("+"),
    REVERSE_NUMBER_STATUS("±"),
    ADD_DECIMAL_POINT("."),
    CALCULATE("=");

    private final String value;

    private Operation(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String[] getValues() {
        List<String> operationsList = Stream.of(Operation.values())
                .map(Operation::getValue)
                .collect(Collectors.toList());

        return operationsList.toArray(new String[0]);
    }

    @Override
    public String toString() {
        return this.getValue();
    }

    public static Operation getOperation(String operationVal) {
        for (Operation operation : Operation.values()) {
            if (operation.value.equals(operationVal)) {
                return operation;
            }
        }
        return null;
    }

    public static boolean isOperation(String operationVal) {
        return AppUtils.arrayContainsValue(getValues(), operationVal);
    }

    public static boolean isDMASOperation(String operation) {
        return operation.equals(Operation.DIVISION.getValue()) ||
                operation.equals(Operation.MULTIPLICATION.getValue()) ||
                operation.equals(Operation.ADDITION.getValue()) ||
                operation.equals(Operation.SUBTRACTION.getValue());
    }

    public static Double calculate(Double operand1, Double operand2, Operation operation) {

        Double result;

        switch (operation) {
            case ADDITION:
                result = add(operand1, operand2);
                break;
            case SUBTRACTION:
                result = subtract(operand1, operand2);
                break;
            case MULTIPLICATION:
                result = multiply(operand1, operand2);
                break;
            case DIVISION:
                result = divide(operand1, operand2);
                break;
            default:
                result = null;
                break;
        }
        return result;
    }

    public static Double add(Double operand1, Double operand2) {
        return operand1 + operand2;
    }

    public static Double subtract(Double operand1, Double operand2) {
        return operand1 - operand2;
    }

    public static Double multiply(Double operand1, Double operand2) {
        return operand1 * operand2;
    }

    public static Double divide(Double operand1, Double operand2) {
       if (operand2 == 0) {
           throw new ArithmeticException(AppConstants.CalculatorConstants.DIVISION_BY_0_ERROR_MSG);
       }

       return operand1 / operand2;
    }
}
