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
    protected static final JLabel vmID = new JLabel("          VM's ID           ");
    protected static JPanel panel;
    protected static GridBagConstraints c;
    protected static Integer i = 0;

    private static final String WINDOW_TITLE = "T2 OOPT DVM";
    private static final int frameWidth = 500;
    private static final int frameHeight = 500;

    protected DvmWindow(Controller controller) {
        this(controller, WINDOW_TITLE);
    }

    protected DvmWindow(Controller controller, String title) {
        super(title);
        DvmWindow.controller = controller;
        items = controller.getItemList();
        this.setSize(frameWidth, frameHeight);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        init();
        this.setVisible(true);
    }

    public abstract void actionPerformed(ActionEvent e);

    protected abstract void init();
}
