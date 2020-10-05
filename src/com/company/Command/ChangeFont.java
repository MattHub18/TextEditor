package com.company.Command;

import com.company.Editor;
import com.company.Utility.TextDecorator;

import javax.swing.*;
import javax.swing.text.StyleConstants;

public class ChangeFont extends TextDecorator implements Command {
    @Override
    public void execute(JFrame frame) {
        JComboBox<?> cb = (JComboBox<?>) ((Editor) frame).getTextToolBar().getComponentAtIndex(6);
        String font = "";
        if (cb.getSelectedItem() != null)
            font = (String) cb.getSelectedItem();
        setDecoration(frame, StyleConstants.FontFamily, font);
    }
}
