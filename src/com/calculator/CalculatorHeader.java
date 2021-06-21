package com.calculator;


import com.calculator.app_constants.AppConstants;

import javax.swing.*;
import java.awt.*;

public class CalculatorHeader extends JPanel {

    CalculatorHeader() {
        setBackground(new Color(
                AppConstants.CalculatorConstants.BG_COLOR_R,
                AppConstants.CalculatorConstants.BG_COLOR_R,
                AppConstants.CalculatorConstants.BG_COLOR_R
        ));
        setPreferredSize(new Dimension(
                AppConstants.CalculatorConstants.HEADER_WIDTH,
                AppConstants.CalculatorConstants.HEADER_HEIGHT
        ));

        setLayout(new BorderLayout());
    }
}
