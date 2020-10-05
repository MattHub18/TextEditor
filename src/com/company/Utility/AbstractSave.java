package com.company.Utility;

import com.company.Editor;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;

public class AbstractSave {

    public void write(JFrame frame, String dir) throws IOException {
        FileWriter saveW = new FileWriter(dir);
        for (String line : ((Editor) frame).getTextArea().getText().split("\r")) {
            saveW.write(line);
        }
        saveW.write("\n\n");
        if (!((Editor) frame).getWordSave().isEmpty()) {
            StringBuilder mapAsString = new StringBuilder();
            mapAsString.append("{}");
            for (WordSave key : ((Editor) frame).getWordSave()) {
                mapAsString.append(key.getText()).append("=")
                        .append(key.getPos()).append(", ")
                        .append(key.getDimFont()).append(", ")
                        .append(key.getNameFont()).append(", ")
                        .append(key.getColorText()).append(", ")
                        .append(key.getColorHighlight()).append("; ");
            }
            mapAsString.delete(mapAsString.length() - 2, mapAsString.length());
            saveW.write(mapAsString.toString());
        }
        saveW.close();
    }
}
