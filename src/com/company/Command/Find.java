package com.company.Command;

import com.company.Editor;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;

public class Find implements Command {
    private final Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.LIGHT_GRAY);
    private JTextField field;
    private int pos = -1;
    private Highlighter.Highlight[] highlightIndex;

    public static int correctPos(String crlf) {
        int index = crlf.indexOf("\n");
        int count = 0;
        while (index != -1) {
            count++;
            crlf = crlf.substring(index + 1);
            index = crlf.indexOf("\n");
        }
        return count;
    }

    @Override
    public void execute(JFrame frame) {
        JDialog find = new JDialog(frame);
        find.setSize(200, 100);
        find.setResizable(false);
        Container container = find.getContentPane();
        field = new JTextField(18);
        container.add(field, BorderLayout.WEST);
        field.setVisible(true);
        JButton findButton = new JButton("Find");
        findButton.addActionListener(e -> find(frame));
        container.add(findButton, BorderLayout.SOUTH);
        find.setVisible(true);
        find.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        find.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (highlightIndex != null) {
                    ((Editor) frame).getTextArea().getHighlighter().removeHighlight(highlightIndex[0]);
                    pos = -1;
                }
            }
        });
    }

    private void find(JFrame frame) {
        String inputString = field.getText();
        String text = ((Editor) frame).getTextArea().getText();
        if (pos != -1)
            ((Editor) frame).getTextArea().getHighlighter().removeHighlight(highlightIndex[0]);
        pos = text.indexOf(inputString, pos);
        if (pos == -1)
            pos = text.indexOf(inputString, pos);
        if (pos != -1) {
            pos -= correctPos(text.substring(0, pos));
            ((Editor) frame).getTextArea().setCaretPosition(pos);
            try {
                ((Editor) frame).getTextArea().getHighlighter().addHighlight(pos, pos + inputString.length(), painter);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
            pos += inputString.length() + 1;
        }
        Highlighter highlighter = ((Editor) frame).getTextArea().getHighlighter();
        highlightIndex = highlighter.getHighlights();
    }

}
