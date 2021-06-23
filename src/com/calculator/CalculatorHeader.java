package com.calculator;

import com.calculator.app_constants.AppConstants;

import javax.swing.*;
import java.awt.*;

public class CalculatorHeader extends JPanel {

    private JLabel label = new JLabel();

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

        setLabelSettings();
        add(label);
    }

    private void setLabelSettings() {
        label.setHorizontalAlignment(JLabel.RIGHT);
        label.setVerticalAlignment(JLabel.BOTTOM);
        label.setFont(new Font("Arial", Font.BOLD, 40));
    }

    public void setText(String text) {
        label.setText(text);
    }
}
