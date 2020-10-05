package com.company.Utility;

public class WordSave {
    private String text;
    private int pos;
    private int dimFont;
    private String nameFont;
    private int colorText;
    private int colorHighlight;

    public WordSave() {
        text = "";
        pos = -1;
        dimFont = 12;
        nameFont = "Dialog";
        colorText = 0;
        colorHighlight = -1;
    }

    public WordSave(String text, int pos, int dimFont, String nameFont, int colorText, int colorHighlight) {
        this.text = text;
        this.pos = pos;
        this.dimFont = dimFont;
        this.nameFont = nameFont;
        this.colorText = colorText;
        this.colorHighlight = colorHighlight;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getDimFont() {
        return dimFont;
    }

    public void setDimFont(int dimFont) {
        this.dimFont = dimFont;
    }

    public String getNameFont() {
        return nameFont;
    }

    public void setNameFont(String nameFont) {
        this.nameFont = nameFont;
    }

    public int getColorText() {
        return colorText;
    }

    public void setColorText(int colorText) {
        this.colorText = colorText;
    }

    public int getColorHighlight() {
        return colorHighlight;
    }

    public void setColorHighlight(int colorHighlight) {
        this.colorHighlight = colorHighlight;
    }
}
