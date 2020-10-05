package com.company.Command;

import com.company.Editor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Actions implements ActionListener {
    private final JFrame frame;
    private final Command command;

    public Actions(JFrame frame, Command c) {
        this.frame = frame;
        command = c;
    }

    public void clickE() {
        command.execute(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("execute".equals(e.getActionCommand())) {
            clickE();
            if (command instanceof Commands) {

                Commands clone = null;
                try {
                    clone = ((Commands) command).clone();
                } catch (CloneNotSupportedException cloneNotSupportedException) {
                    cloneNotSupportedException.printStackTrace();
                }
                Undo.getInstance().insert(clone);
            }
            ((Editor) frame).activeUndo();
        }
    }
}
