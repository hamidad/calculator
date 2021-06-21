package com.calculator;

import com.calculator.app_constants.AppConstants;

import javax.swing.*;
import java.awt.*;

public class CalculatorInteractivePart extends JPanel {

    CalculatorInteractivePart() {
        setPanelSettings();
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
}
