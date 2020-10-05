package com.company.Utility;

public class ClipBoard implements Cloneable {
    private String clip;

    public String getClip() {
        return clip;
    }

    public void setClip(String clip) {
        this.clip = clip;
    }

    public ClipBoard clone() {
        ClipBoard clone = null;
        try {
            clone = (ClipBoard) super.clone();

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }


}
