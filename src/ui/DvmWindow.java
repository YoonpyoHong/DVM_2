package ui;

import domain.app.Controller;

import javax.swing.*;
import java.awt.*;

public class DvmWindow extends JFrame {
    protected static final JPanel CARD_PANEL = new JPanel();
    protected static final JLabel VM_ID_LABEL = new JLabel("          VM's ID           ");
    protected static Controller controller;
    protected static GridBagConstraints constraints;

    private static final String WINDOW_TITLE = "T2 OOPT DVM";
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 500;

    public DvmWindow() { this(WINDOW_TITLE); }

    public DvmWindow(String title) {
        super(title);

        DvmWindow.controller = Controller.getInstance();

        CARD_PANEL.setBackground(Color.decode("#dcebf7"));
        CARD_PANEL.add(new HomeWindow());

        this.add(CARD_PANEL, BorderLayout.CENTER);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
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
