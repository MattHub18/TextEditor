package com.company.Command;

import com.company.Editor;

import javax.swing.*;

public class Close implements Command {
    @Override
    public void execute(JFrame frame) {
        Command s;
        if (!((Editor) frame).getFileName().equals(""))
            s = new Save();
        else
            s = new SaveAs();
        s.execute(frame);
    }
}
