package com.company.Utility;

import com.company.Editor;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.lang.reflect.Field;

public abstract class TextDecorator {

    private static String getColorName(Color c) {
        for (Field f : Color.class.getFields()) {
            try {
                if (f.getType() == Color.class && f.get(null).equals(c)) {
                    return f.getName().toUpperCase();
                }
            } catch (java.lang.IllegalAccessException ignored) {
            }
        }
        return "unknown";
    }

    public void setDecoration(JFrame frame, Object name, Object value) {
        String selected = ((Editor) frame).getTextArea().getSelectedText();
        if (selected != null) {
            StyleContext sc = StyleContext.getDefaultStyleContext();
            AttributeSet aSet = sc.addAttribute(SimpleAttributeSet.EMPTY, name, value);
            int start = ((Editor) frame).getTextArea().getSelectionStart();
            int end = ((Editor) frame).getTextArea().getSelectionEnd();
            ((Editor) frame).getTextArea().setCaretPosition(start);
            try {
                ((Editor) frame).getTextArea().getDocument().remove(start, end - start);
            } catch (BadLocationException ef) {
                ef.printStackTrace();
            }
            ((Editor) frame).getTextArea().setCharacterAttributes(aSet, false);
            ((Editor) frame).getTextArea().replaceSelection(selected);
            createWordSave(frame, start, selected, name, value);
        }
    }

    private void createWordSave(JFrame frame, int start, String selected, Object name, Object value) {
        WordSave save = new WordSave();
        save.setPos(start);
        if (((Editor) frame).getWordSave().contains(save))
            save = ((Editor) frame).getWordSave().get(((Editor) frame).getWordSave().indexOf(save));
        else
            ((Editor) frame).getWordSave().add(save);
        save.setText(selected);

        if (name == StyleConstants.FontSize)
            save.setDimFont((Integer) value);
        else if (name == StyleConstants.FontFamily)
            save.setNameFont((String) value);
        else if (name == StyleConstants.Foreground)
            save.setColorText(getValue((Color) value));
    }

    private int getValue(Color color) {
        int value = 0;
        switch (getColorName(color)) {
            case "BLUE":
                value = 1;
                break;
            case "CYAN":
                value = 2;
                break;
            case "DARK_GRAY":
                value = 3;
                break;
            case "GRAY":
                value = 4;
                break;
            case "GREEN":
                value = 5;
                break;
            case "YELLOW":
                value = 6;
                break;
            case "LIGHT_GRAY":
                value = 7;
                break;
            case "MAGENTA":
                value = 8;
                break;
            case "ORANGE":
                value = 9;
                break;
            case "PINK":
                value = 10;
                break;
            case "RED":
                value = 11;
                break;
        }
        return value;
    }

}
