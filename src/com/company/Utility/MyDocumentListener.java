package com.company.Utility;

import com.company.Editor;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MyDocumentListener implements DocumentListener {

    private final JFrame frame;

    public MyDocumentListener(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        for (WordSave ws : ((Editor) frame).getWordSave()) {
            if (((Editor) frame).getTextArea().getCaretPosition() <= ws.getPos())
                ws.setPos(ws.getPos() + 1);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        for (WordSave ws : ((Editor) frame).getWordSave()) {
            if (((Editor) frame).getTextArea().getCaretPosition() < ws.getPos())
                ws.setPos(ws.getPos() - 1);
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }
}
