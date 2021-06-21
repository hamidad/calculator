package com.calculator.enums;

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

    public static boolean isOperation(String operationVal) {
        return AppUtils.arrayContainsValue(getValues(), operationVal);
    }

}
