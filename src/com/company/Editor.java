package com.company;

import com.company.Command.*;
import com.company.Utility.ClipBoard;
import com.company.Utility.MyDocumentListener;
import com.company.Utility.WordSequence;

import javax.swing.*;
import java.awt.*;

public class Editor extends JFrame {
    private final JTextPane textArea;
    private final WordSequence wordSave;
    private final JToolBar textToolBar;
    private final JButton undoButton;
    private int textColorValue = 0;
    private int highlightColorValue = 1;
    private String fileName = "";


    public Editor() {
        super("Editor");
        ClipBoard board = new ClipBoard();
        wordSave = new WordSequence();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height - 50);

        JMenuBar mb = new JMenuBar();
        mb.setMaximumSize(mb.getPreferredSize());

        undoButton = new JButton(new ImageIcon("res/img/undou.png"));
        undoButton.setBorder(null);
        undoButton.setOpaque(false);
        undoButton.setActionCommand("execute");
        undoButton.addActionListener(new Actions(Editor.this, Undo.getInstance()));
        mb.add(undoButton);

        JMenu f = new JMenu("File");
        createMenu(f, "res/img/open.png", "Open", new Open());
        createMenu(f, "res/img/save.png", "Save", new Save());
        createMenu(f, "res/img/saveas.png", "Save as", new SaveAs());
        mb.add(f);
        JMenu e = new JMenu("Edit");
        createMenu(e, "res/img/cut.png", "Cut", new Cut(board));
        createMenu(e, "res/img/copy.png", "Copy", new Copy(board));
        createMenu(e, "res/img/paste.png", "Paste", new Paste(board));
        mb.add(e);
        JMenu n = new JMenu("Navigate");
        createMenu(n, "res/img/find.png", "Find", new Find());
        createMenu(n, "res/img/replace.png", "Replace", new Replace());
        mb.add(n);

        textToolBar = new JToolBar();
        textToolBar.setFloatable(false);
        addTextButtons();

        textArea = new JTextPane();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        textArea.getDocument().addDocumentListener(new MyDocumentListener(this));

        createLayout(mb, textToolBar, scrollPane);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                new Actions(Editor.this, new Close()).clickE();
                System.exit(0);
            }
        });
    }

    public static Color getColor(int value) {
        Color color = Color.BLACK;
        switch (value) {
            case 1:
                color = Color.BLUE;
                break;
            case 2:
                color = Color.CYAN;
                break;
            case 3:
                color = Color.DARK_GRAY;
                break;
            case 4:
                color = Color.GRAY;
                break;
            case 5:
                color = Color.GREEN;
                break;
            case 6:
                color = Color.YELLOW;
                break;
            case 7:
                color = Color.LIGHT_GRAY;
                break;
            case 8:
                color = Color.MAGENTA;
                break;
            case 9:
                color = Color.ORANGE;
                break;
            case 10:
                color = Color.PINK;
                break;
            case 11:
                color = Color.RED;
                break;
        }
        return color;
    }

    public WordSequence getWordSave() {
        return wordSave;
    }

    public JTextPane getTextArea() {
        return textArea;
    }

    public JToolBar getTextToolBar() {
        return textToolBar;
    }

    public int getColorHighlightSlider() {
        return highlightColorValue;
    }

    public int getTextColor() {
        return textColorValue;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private void createMenu(JMenu m, String name, String text, Command c) {
        ImageIcon Icon = new ImageIcon(name);
        JMenuItem item = new JMenuItem(text, Icon);
        item.setActionCommand("execute");
        item.addActionListener(new Actions(Editor.this, c));
        m.add(item);
    }

    private void addTextButtons() {
        createLabel("Font size", 5);

        String[] fontSizes = new String[10];
        for (int i = 0; i < fontSizes.length; i++)
            fontSizes[i] = Integer.toString(10 + i);
        createComboBox(fontSizes, 2);

        createLabel("Font name", 6);

        String[] fontNames = {"Dialog", "Serif", "Calibri", "Comic Sans MS", "OCR A Extended"};
        createComboBox(fontNames, 0);

        JButton textColor = new JButton("Text Color");
        createColor(0, textColor);
        textToolBar.addSeparator();

        JButton highlightColor = new JButton("Highlight Color");
        createColor(1, highlightColor);
    }

    private void createLabel(String text, int dim) {
        JTextField fn = new JTextField(dim);
        fn.setText(text);
        fn.setEditable(false);
        fn.setMaximumSize(fn.getPreferredSize());
        fn.setBorder(null);
        fn.setOpaque(false);
        textToolBar.add(fn);
        textToolBar.addSeparator(new Dimension(1, 1));
    }

    private void createComboBox(String[] list, int startIndex) {
        JComboBox<String> fontNameList = new JComboBox<>(list);
        fontNameList.setMaximumSize(fontNameList.getPreferredSize());
        fontNameList.setSelectedIndex(startIndex);
        fontNameList.addActionListener(e -> {
            Command c;
            if (startIndex == 0)
                c = new ChangeFont();
            else
                c = new ChangeDimFont();
            c.execute(Editor.this);
        });
        textToolBar.add(fontNameList);
        textToolBar.addSeparator();

    }

    private void createColor(int start, JButton button) {
        JPopupMenu pop = new JPopupMenu();
        GridLayout grid = new GridLayout(4, 3);
        for (int i = start; i < 12; i++) {
            JButton btn = new JButton();
            btn.setSize(10, 10);
            btn.setBackground(getColor(i));
            int finalI = i;
            btn.addActionListener(e -> {
                button.setBackground(getColor(finalI));
                Command c;
                if (start == 0) {
                    textColorValue = finalI;
                    c = new ColorText();
                } else {
                    highlightColorValue = finalI;
                    c = new Highlight();
                }
                c.execute(Editor.this);
            });
            pop.add(btn);
        }
        button.addActionListener(e -> {
            pop.setMaximumSize(pop.getPreferredSize());
            pop.setLayout(grid);
            Point pos = new Point();
            pos.x = (button.getWidth() / 2);
            pos.y = (button.getHeight() / 2);
            pop.show(button, pos.x, pos.y);
        });
        textToolBar.add(button);
    }

    private void createLayout(JComponent... arg) {

        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setHorizontalGroup(gl.createParallelGroup()
                .addComponent(arg[0], GroupLayout.DEFAULT_SIZE,
                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(arg[1], GroupLayout.DEFAULT_SIZE,
                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(arg[2], GroupLayout.DEFAULT_SIZE,
                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
                .addComponent(arg[1])
                .addComponent(arg[2])
        );
    }

    public void activeUndo() {
        if (!Undo.getInstance().isEmpty())
            undoButton.setIcon(new ImageIcon("res/img/undoa.png"));
        else
            undoButton.setIcon(new ImageIcon("res/img/undou.png"));
    }
}

