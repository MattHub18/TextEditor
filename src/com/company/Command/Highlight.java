package com.company.Command;

import com.company.Editor;
import com.company.Utility.WordSave;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class Highlight implements Command {
    @Override
    public void execute(JFrame frame) {
        String selected = ((Editor) frame).getTextArea().getSelectedText();
        if (selected != null) {
            int value = ((Editor) frame).getColorHighlightSlider();
            int start = ((Editor) frame).getTextArea().getSelectionStart();
            int end = ((Editor) frame).getTextArea().getSelectionEnd();
            ((Editor) frame).getTextArea().setCaretPosition(start);
            Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Editor.getColor(value));
            try {
                ((Editor) frame).getTextArea().getHighlighter().addHighlight(start, end, painter);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
            WordSave save = new WordSave();
            save.setPos(start);
            if (((Editor) frame).getWordSave().contains(save))
                save = ((Editor) frame).getWordSave().get(((Editor) frame).getWordSave().indexOf(save));
            else
                ((Editor) frame).getWordSave().add(save);
            save.setText(selected);
            save.setColorHighlight(value);
        }
    }
}
