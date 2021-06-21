package com.calculator.buttons;

import javax.swing.*;

public class Button extends JButton {

    public Button(String text) {
        setText(text);
        setFocusable(false);
        setBorderPainted(false);
    }
}
