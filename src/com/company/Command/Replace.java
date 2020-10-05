package com.company.Command;

import com.company.Editor;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;

public class Replace extends Commands implements Command {

    private int pos = -1;
    private String inputString;
    private String replaceString;

    public static void replace(JFrame frame, String inputString, int start) {
        int finish = start + inputString.length();
        char[] builder = new char[((Editor) frame).getTextArea().getText().length() - inputString.length()];
        int i = 0;
        for (int j = 0; j < ((Editor) frame).getTextArea().getText().length(); j++) {
            if (j < start || j > finish - 1) {
                builder[i] = ((Editor) frame).getTextArea().getText().charAt(j);
                i++;
            }
        }
        ((Editor) frame).getTextArea().setText(new String(builder));
    }

    @Override
    public void execute(JFrame frame) {
        JDialog replace = new JDialog(frame);
        replace.setModal(true);
        replace.setSize(300, 150);
        replace.setResizable(false);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        panel.add(setLabel("Find:"));

        JTextField field = new JTextField(1);
        panel.add(field);
        field.setVisible(true);

        panel.add(setLabel("Replace with:"));

        JTextField repl = new JTextField(1);
        panel.add(repl);
        repl.setVisible(true);

        panel.setVisible(true);
        replace.add(panel);

        JButton findButton = new JButton("Replace");
        findButton.addActionListener(e -> find(frame, field, repl));
        replace.add(findButton, BorderLayout.SOUTH);

        replace.add(panel);
        replace.setVisible(true);
        replace.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        replace.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                pos = -1;
            }
        });
    }

    @Override
    public void undo(JFrame frame) {
        findHelper(frame, replaceString, inputString);
    }

    private JTextField setLabel(String text) {
        JTextField f = new JTextField();
        f.setText(text);
        f.setEditable(false);
        f.setVisible(true);
        return f;
    }

    private void findHelper(JFrame frame, String inputString, String replaceString) {
        String text = ((Editor) frame).getTextArea().getText();
        pos = text.indexOf(inputString, pos);
        if (pos == -1)
            pos = text.indexOf(inputString, pos);
        if (pos != -1) {
            ((Editor) frame).getTextArea().setCaretPosition(pos);
            replace(frame, inputString, ((Editor) frame).getTextArea().getCaretPosition());
            pos -= Find.correctPos(text.substring(0, pos));
            try {
                ((Editor) frame).getTextArea().getDocument().insertString(pos, replaceString, ((Editor) frame).getTextArea().getCharacterAttributes());
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
            pos += inputString.length();
        }
    }

    private void find(JFrame frame, JTextField field, JTextField repl) {
        inputString = field.getText();
        replaceString = repl.getText();
        findHelper(frame, inputString, replaceString);
    }
}
