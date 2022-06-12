package ui;

import domain.product.Item;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static domain.product.ItemManager.MAX_ITEM;
import static ui.DvmWindow.*;

public class Window_1 extends DvmPanel {
    private JButton btn1;
    private JButton btn2;

    private EmptyBorder eb = new EmptyBorder(new Insets(20, 10, 0, 10));
    private Border grayLine = BorderFactory.createLineBorder(Color.decode("#cfd0d1"), 1);

    private static final int btnWidth = 120;
    private static final int btnHeight = 30;
    private static final int drinkPanelWidth = 290;
    private static final int drinkPanelHeight = 400;

    protected static int selectedItemId;

    private Item[] items;

    public Window_1() {
        super(null);
    }

    protected void init() {
        super.init();
        btn1 = new JButton("ADMIN LOGIN");
        btn2 = new JButton("VERIFICATION CODE");
        JPanel panel = new JPanel(new GridBagLayout());
        JPanel itemLayout = new JPanel();

        itemLayout.setPreferredSize(new Dimension(drinkPanelWidth, drinkPanelHeight));
        itemLayout.setBorder(BorderFactory.createCompoundBorder(grayLine, eb));

        c = new GridBagConstraints();
        panel.setBackground(Color.decode("#dcebf7"));
        itemLayout.setBackground(Color.decode("#ebeced"));

        addJLabel(panel);
        addComponent(panel, btn1, 10, 2, 2, 10, 4, 0, 0.5, GridBagConstraints.FIRST_LINE_END);
        addComponent(panel, btn2, 10, 2, 2, 10, 4, 4, 0.5, GridBagConstraints.LINE_END);

        btn1.addActionListener(this);
        btn2.addActionListener(this);

        btn1.setFocusable(false);
        btn2.setFocusable(false);

        // hmm.. Window().init() call once..?
        items = controller.getItemManager().getItemList();
        for (int i = 0; i < MAX_ITEM; i++) {
            JButton[] btn = new JButton[MAX_ITEM];
            btn[i] = new JButton(items[i].getItemName());
//            btn[i] = new JButton("TEST");
            btn[i].setFocusable(false);
            btn[i].setPreferredSize(new Dimension(btnWidth, btnHeight));
            itemLayout.add(btn[i]);
            btn[i].addActionListener(this);
//            System.out.println("init btn[" + i + "]");
        }

        addComponent(panel, itemLayout, 0, 9, 0, 10, 0, 1,3, 10);
        itemLayout.setMinimumSize(new Dimension(280,390));
        CARD.add(panel);
    }

    public void actionPerformed(ActionEvent e) {
        resetCard();
        if (e.getActionCommand().equals("ADMIN LOGIN")) {
            CARD.add(new Window_8(this));
        } else if (e.getActionCommand().equals("VERIFICATION CODE")) {
            CARD.add(new Window_6(this));
        } else {
            for (int id = 0; id < MAX_ITEM; id++) {
                if (e.getActionCommand().equals(items[id].getItemName())) {
                    selectedItemId = id;
                    Item selectedItem = items[id];
                    System.out.println("selected " + selectedItem);
                    CARD.add(new Window_2(this, selectedItem));
                    break;
                }
            }
        }
    }
}
