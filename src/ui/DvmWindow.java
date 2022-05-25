package ui;

import domain.app.Controller;
import domain.product.Item;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public abstract class DvmWindow extends JFrame implements ActionListener {
    protected static Controller controller;
    protected static Item[] items;
    protected final Container frame = this.getContentPane();
    protected final JLabel vmID = new JLabel("          VM's ID           ");
    protected JPanel panel;
    protected GridBagConstraints c;

    private static final String WINDOW_TITLE = "T2 OOPT DVM";
    private static final int frameWidth = 500;
    private static final int frameHeight = 500;

    protected DvmWindow(Controller controller) { this(controller, WINDOW_TITLE); }

    protected DvmWindow(Controller controller, String title) {
        super(title);
        DvmWindow.controller = controller;
        items = controller.getItemList();
        this.setSize(frameWidth, frameHeight);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        init();
        this.setVisible(true);
        System.out.println(this.getClass() + " created.");
    }

    public abstract void actionPerformed(ActionEvent e);

    protected abstract void init();

    protected void addJLable(JLabel label, int top, int left, int bottom, int right, boolean isOpaque) {
        c.insets = new Insets(top, left, bottom, right);
        label.setOpaque(isOpaque);
        panel.add(label, c);
    }

    protected void setJLable(JLabel label, int width, int height, boolean isOpaque, Color color, int thickness) {
        label.setPreferredSize(new Dimension(width, height));
        label.setOpaque(isOpaque);
        label.setBorder(BorderFactory.createLineBorder(color, thickness));
    }

    protected void addComponent(Component comp, int top, int left, int bottom, int right, int gridX, int gridY, double weightX, int anchor) {
        c.insets = new Insets(top, left, bottom, right);
        c.gridx = gridX;
        c.gridy = gridY;
        c.weightx = weightX;
        c.anchor = anchor;
        panel.add(comp, c);
    }

    protected void addComponent(Component comp, int top, int left, int bottom, int right, int gridX, int gridY, int gridWidth, int gridHeight, int anchor) {
        c.insets = new Insets(top, left, bottom, right);
        c.gridx = gridX;
        c.gridy = gridY;
        c.gridwidth = gridWidth;
        c.gridheight = gridHeight;
        c.anchor = anchor;
        panel.add(comp, c);
    }
}
