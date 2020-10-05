package com.company.Utility;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

public class FileChooser extends JFileChooser {

    public FileChooser(String path) {
        super(path);
    }

    public FileFilter getFilter() {
        return new FileFilter() {

            public String getDescription() {
                return "Editor Files (*.eee)";
            }

            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    String filename = f.getName().toLowerCase();
                    return filename.endsWith(".eee");
                }
            }
        };
    }
}
