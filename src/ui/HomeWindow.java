package ui;

import domain.product.Item;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

import static domain.product.ItemManager.MAX_ITEM;

import static ui.DvmWindow.controller;
import static ui.DvmWindow.addComponent;
import static ui.DvmWindow.CARD_PANEL;

// Window1
public class HomeWindow extends DvmPanel {
    protected int selectedItemId = -1;

    private final EmptyBorder emptyBorder = new EmptyBorder(new Insets(20, 10, 0, 10));
    private final Border grayLineBorder = BorderFactory.createLineBorder(Color.decode("#cfd0d1"), 1);

    private static final int BUTTON_WIDTH = 120;
    private static final int BUTTON_HEIGHT = 30;

    private static final int DRINK_PANEL_WIDTH = 290;
    private static final int DRINK_PANEL_HEIGHT = 400;

    private Item[] itemArray;

    public HomeWindow() {
        super();
        init();
    }

    @Override
    void init() {
        initLayout();
    }

    @Override
    protected void initLayout() {
        super.initLayout();

        JPanel itemLayoutPanel = new JPanel();
        itemLayoutPanel.setPreferredSize(new Dimension(DRINK_PANEL_WIDTH, DRINK_PANEL_HEIGHT));
        itemLayoutPanel.setBorder(BorderFactory.createCompoundBorder(grayLineBorder, emptyBorder));
        itemLayoutPanel.setBackground(Color.decode("#ebeced"));
        itemLayoutPanel.setMinimumSize(new Dimension(280,390));
        addComponent(mainPanel, itemLayoutPanel, 0, 9, 0, 10, 0, 1,3, 10);

        JButton adminLoginBtn = new JButton("ADMIN LOGIN");
        adminLoginBtn.addActionListener(this);
        adminLoginBtn.setFocusable(false);
        addComponent(mainPanel, adminLoginBtn, 10, 2, 2, 10, 4, 0, 0.5, GridBagConstraints.FIRST_LINE_END);

        JButton verificationBtn = new JButton("VERIFICATION CODE");
        verificationBtn.addActionListener(this);
        verificationBtn.setFocusable(false);
        addComponent(mainPanel, verificationBtn, 10, 2, 2, 10, 4, 4, 0.5, GridBagConstraints.LINE_END);

        itemArray = controller.getItemManager().getItemList();
        initItemButtons(itemArray, itemLayoutPanel);
    }

    private void initItemButtons(Item[] itemArray, JPanel itemLayoutPanel) {
        JButton[] itemBtnArray = new JButton[MAX_ITEM];
        for (int i = 0; i < MAX_ITEM; i++) {
            itemBtnArray[i] = new JButton(itemArray[i].getItemName());
            itemBtnArray[i].setFocusable(false);
            itemBtnArray[i].setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
            itemBtnArray[i].addActionListener(this);
            itemLayoutPanel.add(itemBtnArray[i]);
        }
    }

    public void actionPerformed(ActionEvent e) {
        resetCard();
        if (e.getActionCommand().equals("ADMIN LOGIN")) {
            CARD_PANEL.add(new AdminLoginWindow(this));
        } else if (e.getActionCommand().equals("VERIFICATION CODE")) {
            CARD_PANEL.add(new ReadVerificationWindow(this));
        } else {
            for (int id = 0; id < MAX_ITEM; id++) {
                if (e.getActionCommand().equals(itemArray[id].getItemName())) {
                    selectedItemId = id;
                    Item selectedItem = itemArray[id];
                    System.out.println("HomeWindow: selected " + selectedItem);
                    CARD_PANEL.add(new ItemShowWindow(this, selectedItem));
                    break;
                }
            }
        }
    }
}
