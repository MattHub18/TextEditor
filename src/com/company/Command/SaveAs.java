package com.company.Command;

import com.company.Editor;
import com.company.Utility.AbstractSave;
import com.company.Utility.FileChooser;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class SaveAs implements Command {

    @Override
    public void execute(JFrame frame) {
        FileChooser c = new FileChooser("saves");
        c.setFileFilter(c.getFilter());
        int rVal = c.showSaveDialog(frame);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            String name = c.getSelectedFile().getName();
            String dir = c.getCurrentDirectory().toString() + "\\" + name;
            if (!dir.endsWith(".eee"))
                dir += ".eee";
            try {
                File save = new File(dir);
                if (save.createNewFile()) {
                    if (((Editor) frame).getFileName().equals(""))
                        ((Editor) frame).setFileName(dir);
                    AbstractSave as = new AbstractSave();
                    as.write(frame, dir);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
