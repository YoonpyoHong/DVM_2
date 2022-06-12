package ui;

import domain.app.Controller;
import domain.product.Item;

import javax.swing.*;
import java.awt.*;

public class DvmWindow extends JFrame {
    private static final String WINDOW_TITLE = "T2 OOPT DVM";
    protected static final JLabel VM_ID = new JLabel("          VM's ID           ");
    protected static Controller controller;
    protected static Item[] items;
    public static final JPanel CARD = new JPanel();
    //    Container frame = this.getContentPane();
    protected static GridBagConstraints c;

    private static final int frameWidth = 500;
    private static final int frameHeight = 500;

    public DvmWindow(Controller controller) { this(controller, WINDOW_TITLE); }

    public DvmWindow(Controller controller, String title) {
        super(title);
        DvmWindow.controller = controller;
        items = controller.getItemList();
        CARD.setBackground(Color.decode("#dcebf7"));
        CARD.add(new Window_1());
        this.add(CARD, BorderLayout.CENTER);
        this.setSize(frameWidth,frameHeight);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
//        System.out.println(this.getClass() + " created.");
    }

    protected static void addJLabel(JPanel panel) {
        c.insets = new Insets(2, 2, 2, 2);
        DvmWindow.VM_ID.setBackground(Color.decode("#cfd0d1"));
        DvmWindow.VM_ID.setOpaque(true);
        panel.add(DvmWindow.VM_ID, c);
    }

    protected static void setJLabel(JLabel label, int width, int height, Color color) {
        label.setPreferredSize(new Dimension(width, height));
        label.setOpaque(true);
        label.setBorder(BorderFactory.createLineBorder(color, 1));
    }

    protected static void addComponent(JPanel panel, Component comp, int top, int left, int bottom, int right, int gridX, int gridY, double weightX, int anchor) {
        c.insets = new Insets(top, left, bottom, right);
        c.gridx = gridX;
        c.gridy = gridY;
        c.weightx = weightX;
        c.anchor = anchor;
        panel.add(comp, c);
    }

    protected static void addComponent(JPanel panel, Component comp, int top, int left, int bottom, int right, int gridX, int gridY, int gridWidth, int gridHeight, int anchor) {
        c.insets = new Insets(top, left, bottom, right);
        c.gridx = gridX;
        c.gridy = gridY;
        c.gridwidth = gridWidth;
        c.gridheight = gridHeight;
        c.anchor = anchor;
        panel.add(comp, c);
    }
}
