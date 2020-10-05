package com.company.Command;

import javax.swing.*;

public abstract class Commands implements Cloneable {
    @Override
    protected Commands clone() throws CloneNotSupportedException {

        return (Commands) super.clone();
    }

    public abstract void undo(JFrame frame);
}
