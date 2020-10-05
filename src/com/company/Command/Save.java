package com.company.Command;

import com.company.Editor;
import com.company.Utility.AbstractSave;

import javax.swing.*;
import java.io.IOException;

public class Save implements Command {
    @Override
    public void execute(JFrame frame) {
        if (!((Editor) frame).getFileName().equals("")) {
            try {
                AbstractSave as = new AbstractSave();
                as.write(frame, ((Editor) frame).getFileName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Command c = new SaveAs();
            c.execute(frame);
        }
    }
}
