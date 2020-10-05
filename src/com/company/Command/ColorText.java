package com.company.Command;

import com.company.Editor;
import com.company.Utility.TextDecorator;

import javax.swing.*;
import javax.swing.text.StyleConstants;

public class ColorText extends TextDecorator implements Command {

    @Override
    public void execute(JFrame frame) {
        int value = ((Editor) frame).getTextColor();
        setDecoration(frame, StyleConstants.Foreground, Editor.getColor(value));
    }
}
