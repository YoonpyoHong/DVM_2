package ui;

import domain.app.Controller;

import javax.swing.*;
import java.awt.*;

public class DvmWindow extends JFrame {
    private static final String WINDOW_TITLE = "T2 OOPT DVM";
    protected static final JLabel VM_ID = new JLabel("          VM's ID           ");
    protected static Controller controller;
    protected static GridBagConstraints constraints;
    public static final JPanel CARD = new JPanel();
    //    Container frame = this.getContentPane();

    private static final int frameWidth = 500;
    private static final int frameHeight = 500;

    public DvmWindow() { this(WINDOW_TITLE); }

    public DvmWindow(String title) {
        super(title);

        DvmWindow.controller = Controller.getInstance();

        CARD.setBackground(Color.decode("#dcebf7"));
        CARD.add(new HomeWindow());

        this.add(CARD, BorderLayout.CENTER);
        this.setSize(frameWidth, frameHeight);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        System.out.println(this.getClass() + " created.");
    }

    protected static void addJLabel(JPanel panel) {
        constraints.insets = new Insets(2, 2, 2, 2);
        DvmWindow.VM_ID.setBackground(Color.decode("#cfd0d1"));
        DvmWindow.VM_ID.setOpaque(true);
        panel.add(DvmWindow.VM_ID, constraints);
    }

    protected static void setJLabel(JLabel label, int width, int height, Color color) {
        label.setPreferredSize(new Dimension(width, height));
        label.setOpaque(true);
        label.setBorder(BorderFactory.createLineBorder(color, 1));
    }

    private static void setConstraints(int top, int left, int bottom, int right, int gridX, int gridY, int anchor) {
        constraints.insets = new Insets(top, left, bottom, right);
        constraints.gridx = gridX;
        constraints.gridy = gridY;
        constraints.anchor = anchor;
    }

    protected static void addComponent(JPanel panel, Component comp, int top, int left, int bottom, int right, int gridX, int gridY, double weightX, int anchor) {
        setConstraints(top, left, bottom, right, gridX, gridY, anchor);
        constraints.weightx = weightX;
        panel.add(comp, constraints);
    }

    protected static void addComponent(JPanel panel, Component comp, int top, int left, int bottom, int right, int gridX, int gridY, int gridWidth, int gridHeight, int anchor) {
        setConstraints(top, left, bottom, right, gridX, gridY, anchor);
        constraints.gridwidth = gridWidth;
        constraints.gridheight = gridHeight;
        panel.add(comp, constraints);
    }
}
