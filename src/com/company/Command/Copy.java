package com.company.Command;

import com.company.Editor;
import com.company.Utility.ClipBoard;

import javax.swing.*;

public class Copy implements Command {
    private final ClipBoard board;


    public Copy(ClipBoard board) {
        this.board = board;
    }

    @Override
    public void execute(JFrame frame) {
        board.setClip(((Editor) frame).getTextArea().getSelectedText());
    }

}
