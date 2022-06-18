package ui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

import static ui.DvmWindow.CARD_PANEL;
import static ui.DvmWindow.constraints;


public abstract class DvmPanel extends JPanel implements ActionListener {
    protected DvmPanel prevPanel;
    protected JPanel mainPanel;

    public DvmPanel() {
        this(null);
    }

    public DvmPanel(DvmPanel prevPanel) {
        System.out.println(this.getClass() + "()");
        this.prevPanel = prevPanel;
    }

    abstract void init();

    protected void initLayout() {
        System.out.println(this.getClass() + ".initLayout()");
        initCommonLayout();
    }

    private void initCommonLayout() {
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.decode("#dcebf7"));
        constraints = new GridBagConstraints();
        CARD_PANEL.add(mainPanel);
        addVmIdLabel();
    }

    protected void addVmIdLabel() {
        constraints.insets = new Insets(2, 2, 2, 2);
        DvmWindow.VM_ID_LABEL.setBackground(Color.decode("#cfd0d1"));
        DvmWindow.VM_ID_LABEL.setOpaque(true);
        mainPanel.add(DvmWindow.VM_ID_LABEL, constraints);
    }

    protected static void resetCard() {
        CARD_PANEL.removeAll();
        CARD_PANEL.revalidate();
        CARD_PANEL.repaint();
        System.out.println(String.format("%s: resetCard()", "DvmPanel"));
    }
}
