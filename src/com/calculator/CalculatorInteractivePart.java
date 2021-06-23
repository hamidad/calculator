package com.calculator;

import com.calculator.app_constants.AppConstants;
import com.calculator.app_utils.AppUtils;
import com.calculator.buttons.DigitButton;
import com.calculator.buttons.OperationButton;
import com.calculator.enums.Operation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class CalculatorInteractivePart extends JPanel {

    private String calculatorStr = ""; // Set of numbers and operations in string form

    private static final String[] calculatorElements = {
            Operation.CLEAR_ENTRY.getValue(),
            Operation.CLEAR.getValue(),
            Operation.CLEAR_PREV_NUM.getValue(),
            Operation.DIVISION.getValue(),
            "7",
            "8",
            "9",
            Operation.MULTIPLICATION.getValue(),
            "4",
            "5",
            "6",
            Operation.SUBTRACTION.getValue(),
            "1",
            "2",
            "3",
            Operation.ADDITION.getValue(),
            Operation.REVERSE_NUMBER_STATUS.getValue(),
            "0",
            Operation.ADD_DECIMAL_POINT.getValue(),
            Operation.CALCULATE.getValue()
    };

    private static final String[] HIGH_PRECEDENCE_OPERATIONS = {
            Operation.DIVISION.getValue(),
            Operation.MULTIPLICATION.getValue()
    };

    private static final String[] LOW_PRECEDENCE_OPERATIONS = {
            Operation.ADDITION.getValue(),
            Operation.SUBTRACTION.getValue()
    };

    private CalculatorHeader calculatorHeader;

    CalculatorInteractivePart(CalculatorHeader calculatorHeader) {
        this.calculatorHeader = calculatorHeader;

        setPanelSettings();

        for (String calculatorElement : calculatorElements) {
            if (Operation.isOperation(calculatorElement)) {
                handleOperation(calculatorElement);
            } else {
                handleDigitButton(calculatorElement);
            }
        }
    }

    private void setPanelSettings() {
        GridLayout layout = new GridLayout(0, 4, 5, 5);
        setLayout(layout);

        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setBackground(new Color(
                AppConstants.CalculatorConstants.BG_COLOR_R,
                AppConstants.CalculatorConstants.BG_COLOR_R,
                AppConstants.CalculatorConstants.BG_COLOR_R)
        );
        setPreferredSize(new Dimension(
                AppConstants.CalculatorConstants.INTERACTIVE_CALC_PART_WIDTH,
                AppConstants.CalculatorConstants.INTERACTIVE_CALC_PART_HEIGHT
        ));
    }

    private void handleDigitButton(String number) {
        DigitButton button = new DigitButton(number);
        addDigitButtonToPanel(button);
        addDigitButtonMousePressedListener(button);
    }

    private void addDigitButtonToPanel(DigitButton button) {
        add(button);
    }

    private void addDigitButtonMousePressedListener(DigitButton button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                digitButtonClicked(button);
            }
        });
    }

    private void digitButtonClicked(DigitButton button) {
        calculatorStr += button.getText();
    }

    private void handleOperation(String operation) {
        OperationButton button = new OperationButton(operation);
        addOperationButtonToPanel(button);
        addOperationButtonMousePressedListener(button);
    }

    private void addOperationButtonToPanel(OperationButton button) {
        add(button);
    }

    private void addOperationButtonMousePressedListener(OperationButton button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                operationButtonClicked(button);
            }
        });
    }

    private void operationButtonClicked(OperationButton button) {

        String operationVal = button.getText();

        /**
         * If chosen operation doesn't belong to predefined in Operation enum or
         * calculatorStr is empty (eg. no number)
         * Return
         */
        if (!Operation.isOperation(operationVal) ||
                calculatorStr.equals("")) {
            return;
        }

        /**
         * On CLEAR, clear/empty calculator
         */
        if (operationVal.equals(Operation.CLEAR.getValue())) {
            clearCalculator();
            return;
        }

        String lastCalcChar = AppUtils.getLastChar((calculatorStr));

        /**
         * If last entered operation is DMAS
         * Return
         */
        if (Operation.isDMASOperation(lastCalcChar) ||
                lastCalcChar.equals(Operation.ADD_DECIMAL_POINT.getValue())) {
            return;
        }

        /**
         * TODO - Define action on ± operation click
         */
        if (operationVal.equals(Operation.REVERSE_NUMBER_STATUS.getValue())) {
            return;
        }

        if (operationVal.equals(Operation.CLEAR_ENTRY.getValue())) {
            calculatorStr = clearLastDigitEntry(calculatorStr);
            return;
        }

        if (operationVal.equals(Operation.CLEAR_PREV_NUM.getValue())) {
            calculatorStr = clearLastNumber(calculatorStr);
            return;
        }

        /**
         * Calculate result
         */
        if (operationVal.equals(Operation.CALCULATE.getValue())) {
            String result = calculateFinalResult(calculatorStr);
            clearCalculator();
            return;
        }

        if (!Operation.isDMASOperation(lastCalcChar)) {
            calculatorStr += button.getText();
        }
    }

    private void clearCalculator() {
        calculatorStr = "";
    }

    /**
     * This function takes String calculatorStr as parameter and keeps removing last chars from string as long
     * as they are digits
     * @param calculatorStr
     * @return String
     */
    private String clearLastDigitEntry(String calculatorStr) {
        if (calculatorStr.equals("")) {
            return null;
        }

        char[] calculatorCharts = calculatorStr.toCharArray();
        int i = calculatorCharts.length - 1;

        while (true) {
            char character = calculatorCharts[i];
            boolean charIsDigit = AppUtils.charIsDigit(character);

            if (!charIsDigit) {
                break;
            }

            calculatorStr = AppUtils.removeLastChar(calculatorStr);
            i--;
        }

        return calculatorStr;
    }

    /**
     * This function checks for last char in string and removes it if it is digit
     * @param calculatorStr
     * @return
     */
    private String clearLastNumber(String calculatorStr) {
        boolean charIsDigit = AppUtils.getLastChar(calculatorStr).matches("\\d+");
        if (charIsDigit) {
            calculatorStr = AppUtils.removeLastChar(calculatorStr);
        }
        return calculatorStr;
    }

    private String calculateFinalResult(String calculatorStr) {
        // Split string by operations, including DMAS operations
        String[] calculatorElementsArray = calculatorStr.split("(?=[÷×+–]+)|(?<=[÷×+–])");

        // Creating an ArrayList to be able change/remove elements
        ArrayList<String> calculatorElements = new ArrayList<String>(Arrays.asList(calculatorElementsArray));

        calculatorElements = calculate(calculatorElements);

        // Result is only left element from array list
        return calculatorElements.get(0);
    }


    private ArrayList<String> calculate(ArrayList<String> calculatorElements) {
        while (containsHighPrecedenceOperations(calculatorElements)) {
            calculatorElements = calculatePerOperations(calculatorElements, HIGH_PRECEDENCE_OPERATIONS);
        }

        calculatorElements = calculatePerOperations(calculatorElements, LOW_PRECEDENCE_OPERATIONS);

        return calculatorElements;
    }

    /**
     * This functions takes as parameters
     * @param calculatorElements which is list of String elements (numbers and operations) eg. "34.4", "+", "34", "*", "100"
     * @param operations which should calculate pairs of numbers (num1, operation, num2), calculate result,
     *                   set result as num1, remove operation and num2 from array list.
     *                   Do this recursively (while provided operations are still in the array list)
     * @return new updated ArrayList<String>
     */
    private ArrayList<String> calculatePerOperations(ArrayList<String> calculatorElements, String[] operations) {
        if (calculatorElements.size() == 0) {
            return null;
        }

        for (int i = 0; i < calculatorElements.size(); i++) {

            // Take calc element which could be a string number or string operation
            String calcElement = calculatorElements.get(i);

            // Do action only if string represents DMAS operation
            if (Operation.isDMASOperation(calcElement)) {

                Operation operation = Operation.getOperation(calcElement);

                if (AppUtils.arrayContainsValue(operations, calcElement)) {

                    int prevElIndex = i - 1;
                    int operationElIndex = i;
                    int nextElIndex = i + 1;

                    String previousElement = calculatorElements.get(prevElIndex);
                    String nextElement = calculatorElements.get(nextElIndex);

                    Double operand1 = Double.parseDouble(previousElement);
                    Double operand2 = Double.parseDouble(nextElement);
                    Double result = Operation.calculate(operand1, operand2, operation);

                    calculatorElements.set(prevElIndex, Double.toString(result));
                    calculatorElements.remove(nextElIndex);
                    calculatorElements.remove(operationElIndex);

                    /**
                     * Eg. {"2", "*", "2"...} will produce {"4"...}
                     */

                    // Repeat process while there are operations in the list
                    calculatePerOperations(calculatorElements, operations);
                }
            }
        }

        return calculatorElements;
    }

    private boolean containsHighPrecedenceOperations(ArrayList<String> calculatorElements) {
        for (String operation : HIGH_PRECEDENCE_OPERATIONS) {
            if (calculatorElements.contains(operation)) {
                return true;
            }
        }
        return false;
    }
}
