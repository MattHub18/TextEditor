package com.company.Command;

import com.company.Editor;
import com.company.Utility.ClipBoard;

import javax.swing.*;

public class Cut extends Commands implements Command {
    private ClipBoard board;
    private Paste p;

    public Cut(ClipBoard board) {
        this.board = board;
    }

    @Override
    public void execute(JFrame frame) {
        board.setClip(((Editor) frame).getTextArea().getSelectedText());
        p = new Paste(board.clone());
        p.undo(frame);
    }

    @Override
    public void undo(JFrame frame) {
        p.execute(frame);
    }

    @Override
    protected Commands clone() throws CloneNotSupportedException {
        Cut c = (Cut) super.clone();
        c.board = this.board.clone();
        return c;
    }
}
