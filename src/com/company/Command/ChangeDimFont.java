package com.company.Command;

import com.company.Editor;
import com.company.Utility.TextDecorator;

import javax.swing.*;
import javax.swing.text.StyleConstants;


public class ChangeDimFont extends TextDecorator implements Command {
    @Override
    public void execute(JFrame frame) {
        JComboBox<?> cb = (JComboBox<?>) ((Editor) frame).getTextToolBar().getComponentAtIndex(2);
        int dim = 0;
        if (cb.getSelectedItem() != null)
            dim = Integer.parseInt((String) cb.getSelectedItem());
        setDecoration(frame, StyleConstants.FontSize, dim);
    }
}
