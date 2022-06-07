package ui;

import domain.app.Controller;
import domain.product.Item;
import domain.product.ItemManager;

import java.awt.*;
import javax.swing.*;

public class DvmWindow extends JFrame {
    private static final String WINDOW_TITLE = "T2 OOPT DVM";
    protected static final JLabel vmID = new JLabel("          VM's ID           ");
    protected static Controller controller;
    protected static Item[] items;
//    Container frame = this.getContentPane();
    static final JPanel card = new JPanel();
    protected static GridBagConstraints c;

    private static final int frameWidth = 500;
    private static final int frameHeight = 500;

    public DvmWindow(Controller controller) { this(controller, WINDOW_TITLE); }

    public DvmWindow(Controller controller, String title) {
        super(title);
        DvmWindow.controller = controller;
        items = controller.getItemList();
        card.setBackground(Color.decode("#dcebf7"));
        card.add(new Window_1());
        this.add(card, BorderLayout.CENTER);
        this.setSize(frameWidth,frameHeight);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
//        System.out.println(this.getClass() + " created.");
    }

    protected static void addJLabel(JPanel panel) {
        c.insets = new Insets(2, 2, 2, 2);
        DvmWindow.vmID.setBackground(Color.decode("#cfd0d1"));
        DvmWindow.vmID.setOpaque(true);
        panel.add(DvmWindow.vmID, c);
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
