package com.company.Command;

import com.company.Editor;
import com.company.Utility.ClipBoard;

import javax.swing.*;
import javax.swing.text.BadLocationException;

public class Paste extends Commands implements Command {
    private ClipBoard board;

    public Paste(ClipBoard board) {
        this.board = board;
    }

    @Override
    public void execute(JFrame frame) {
        try {
            ((Editor) frame).getTextArea().getDocument().insertString(((Editor) frame).getTextArea().getCaretPosition(), board.getClip(), ((Editor) frame).getTextArea().getCharacterAttributes());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void undo(JFrame frame) {
        if (board.getClip() != null) {
            int pos = ((Editor) frame).getTextArea().getCaretPosition();
            String s;
            try {
                s = ((Editor) frame).getTextArea().getText().substring(pos, pos + board.getClip().length());
            } catch (StringIndexOutOfBoundsException e) {
                s = ((Editor) frame).getTextArea().getText().substring(pos);
                String nullString = "";
                for (int i = 0; i < board.getClip().length(); i++) {
                    nullString = nullString.concat("ç");
                }
                s = s.concat(nullString);
            }
            if (!s.equals(board.getClip()))
                pos -= board.getClip().length();
            Replace.replace(frame, board.getClip(), pos);
        }
    }

    @Override
    protected Commands clone() throws CloneNotSupportedException {
        Paste p = (Paste) super.clone();
        p.board = this.board.clone();
        return p;
    }
}
