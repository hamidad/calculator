package com.calculator;


import com.calculator.app_constants.AppConstants;

import javax.swing.*;
import java.awt.*;

public class CalculatorFrame extends JFrame {

    CalculatorHeader header;

    CalculatorFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(
                AppConstants.CalculatorConstants.FRAME_WIDTH,
                AppConstants.CalculatorConstants.FRAME_HEIGHT
        );
        setTitle(AppConstants.CalculatorConstants.TITLE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(
                new Color(
                        AppConstants.CalculatorConstants.BG_COLOR_R,
                        AppConstants.CalculatorConstants.BG_COLOR_G,
                        AppConstants.CalculatorConstants.BG_COLOR_B
                ));

        addHeader();
        addInteractivePart();
        setResizable(false);
        setVisible(true);
    }

    private void addHeader() {
        header = new CalculatorHeader();
        add(header, BorderLayout.NORTH);
    }

    private void addInteractivePart() {
        CalculatorInteractivePart interactivePart = new CalculatorInteractivePart();
        add(interactivePart, BorderLayout.SOUTH);
    }
}
