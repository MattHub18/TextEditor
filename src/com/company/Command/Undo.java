package com.company.Command;

import javax.swing.*;
import java.util.Stack;

public class Undo implements Command {
    private static Undo ins = null;
    private final Stack<Commands> list;

    private Undo() {
        list = new Stack<>();
    }

    public static Undo getInstance() {
        if (ins == null)
            ins = new Undo();
        return ins;
    }

    @Override
    public void execute(JFrame frame) {
        if (!list.empty()) {
            Commands c = remove();
            c.undo(frame);
        }
    }

    public void insert(Commands c) {
        list.push(c);
    }

    private Commands remove() {
        return list.pop();
    }

    public boolean isEmpty() {
        return list.empty();
    }
}
