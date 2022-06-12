package ui;

import domain.product.Item;

import javax.swing.*;

import java.awt.event.ActionListener;

import static ui.DvmWindow.CARD;


public abstract class DvmPanel extends JPanel implements ActionListener {
    protected DvmPanel prevPanel;
    protected Item selectedItem;

    public DvmPanel() {
        this(null, null);
    }

    public DvmPanel(DvmPanel prevPanel) {
        this(prevPanel, null);
    }

    public DvmPanel(DvmPanel prevPanel, Item selectedItem) {
        System.out.println(this.getClass() + "()");
        this.prevPanel = prevPanel;
        this.selectedItem = selectedItem;
        init();
    }

    void init() {
        System.out.println(this.getClass() + ".init()");
    }

    protected static void resetCard() {
        CARD.removeAll();
        CARD.revalidate();
        CARD.repaint();
        System.out.println(String.format("%s: resetCard()", "DvmPanel"));
    }
}
