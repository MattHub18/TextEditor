package com.company.Command;

import com.company.Editor;
import com.company.Utility.FileChooser;
import com.company.Utility.WordSave;

import javax.swing.*;
import javax.swing.text.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Open implements Command {
    @Override
    public void execute(JFrame frame) {
        FileChooser c = new FileChooser("saves");
        c.setFileFilter(c.getFilter());
        int rVal = c.showOpenDialog(frame);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            String name = c.getSelectedFile().getName();
            String dir = c.getCurrentDirectory().toString() + "\\" + name;
            try {
                ((Editor) frame).setFileName(dir);
                BufferedReader reader = new BufferedReader(new FileReader(dir));
                ((Editor) frame).getTextArea().setText("");
                String next, line = reader.readLine();
                for (boolean last = (line == null); !last; line = next) {
                    last = ((next = reader.readLine()) == null);
                    try {
                        if (!last && !line.equals("")) {
                            ((Editor) frame).getTextArea().getDocument().insertString(((Editor) frame).getTextArea().getCaretPosition(), line, ((Editor) frame).getTextArea().getCharacterAttributes());
                            ((Editor) frame).getTextArea().getDocument().insertString(((Editor) frame).getTextArea().getCaretPosition(), "\n", ((Editor) frame).getTextArea().getCharacterAttributes());
                        } else {
                            if (line.startsWith("{}")) {
                                line = line.substring(2);
                                setFont(frame, line);
                            } else
                                ((Editor) frame).getTextArea().getDocument().insertString(((Editor) frame).getTextArea().getCaretPosition(), line, ((Editor) frame).getTextArea().getCharacterAttributes());
                        }
                    } catch (BadLocationException e) {
                        e.printStackTrace();
                    }
                    if (last)
                        try {
                            ((Editor) frame).getTextArea().setCaretPosition(((Editor) frame).getTextArea().getDocument().getLength() - 1);
                            ((Editor) frame).getTextArea().getDocument().remove(((Editor) frame).getTextArea().getCaretPosition(), 1);
                        } catch (BadLocationException e) {
                            e.printStackTrace();
                        }
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setFont(JFrame frame, String fontGroup) {

        String[] pairs = fontGroup.split(";");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            String[] values = keyValue[1].split(", ");
            ((Editor) frame).getWordSave().add(new WordSave(keyValue[0],
                    Integer.parseInt(values[0]),
                    Integer.parseInt(values[1]),
                    values[2],
                    Integer.parseInt(values[3]),
                    Integer.parseInt(values[4])
            ));
        }
        for (WordSave selected : ((Editor) frame).getWordSave()) {
            if (selected.getText() != null) {
                StyleContext sc = StyleContext.getDefaultStyleContext();
                AttributeSet aSet = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.FontSize, selected.getDimFont());
                aSet = sc.addAttribute(aSet, StyleConstants.FontFamily, selected.getNameFont());
                aSet = sc.addAttribute(aSet, StyleConstants.Foreground, Editor.getColor(selected.getColorText()));
                int start = selected.getPos();
                start -= Find.correctPos(((Editor) frame).getTextArea().getText().substring(0, start));
                ((Editor) frame).getTextArea().setCaretPosition(start);
                try {
                    ((Editor) frame).getTextArea().getDocument().remove(start, selected.getText().length());
                } catch (BadLocationException ef) {
                    ef.printStackTrace();
                }
                ((Editor) frame).getTextArea().setCharacterAttributes(aSet, false);
                ((Editor) frame).getTextArea().replaceSelection("*" + selected.getText());
                try {
                    ((Editor) frame).getTextArea().setCaretPosition(start);
                    ((Editor) frame).getTextArea().getDocument().remove(start, 1);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }

                if (selected.getColorHighlight() != -1) {
                    int value = selected.getColorHighlight();
                    ((Editor) frame).getTextArea().setCaretPosition(start);
                    Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Editor.getColor(value));
                    try {
                        ((Editor) frame).getTextArea().getHighlighter().addHighlight(start, start + selected.getText().length(), painter);
                    } catch (BadLocationException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
