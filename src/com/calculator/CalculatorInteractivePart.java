package com.calculator;

import com.calculator.app_constants.AppConstants;
import com.calculator.buttons.DigitButton;
import com.calculator.buttons.OperationButton;
import com.calculator.enums.Operation;

import javax.swing.*;
import java.awt.*;

public class CalculatorInteractivePart extends JPanel {

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

    CalculatorInteractivePart() {
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
    }

    private void addDigitButtonToPanel(DigitButton button) {
        add(button);
    }

    private void handleOperation(String operation) {
        OperationButton button = new OperationButton(operation);
        addOperationButtonToPanel(button);
    }

    private void addOperationButtonToPanel(OperationButton button) {
        add(button);
    }
}
