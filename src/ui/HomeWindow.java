package ui;

import domain.product.Item;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

import static domain.product.ItemManager.MAX_ITEM;
import static ui.DvmWindow.*;

// Window1
public class HomeWindow extends DvmPanel {
    private JButton adminLoginBtn;
    private JButton verificationBtn;

    private EmptyBorder eb = new EmptyBorder(new Insets(20, 10, 0, 10));
    private Border grayLine = BorderFactory.createLineBorder(Color.decode("#cfd0d1"), 1);

    private static final int btnWidth = 120;
    private static final int btnHeight = 30;
    private static final int drinkPanelWidth = 290;
    private static final int drinkPanelHeight = 400;

    protected static int selectedItemId;

    private Item[] items;

    public HomeWindow() {
        super(null);
    }

    protected void init() {
        super.init();
        adminLoginBtn = new JButton("ADMIN LOGIN");
        verificationBtn = new JButton("VERIFICATION CODE");
        JPanel panel = new JPanel(new GridBagLayout());
        JPanel itemLayout = new JPanel();

        itemLayout.setPreferredSize(new Dimension(drinkPanelWidth, drinkPanelHeight));
        itemLayout.setBorder(BorderFactory.createCompoundBorder(grayLine, eb));

        constraints = new GridBagConstraints();
        panel.setBackground(Color.decode("#dcebf7"));
        itemLayout.setBackground(Color.decode("#ebeced"));

        addJLabel(panel);
        addComponent(panel, adminLoginBtn, 10, 2, 2, 10, 4, 0, 0.5, GridBagConstraints.FIRST_LINE_END);
        addComponent(panel, verificationBtn, 10, 2, 2, 10, 4, 4, 0.5, GridBagConstraints.LINE_END);

        adminLoginBtn.addActionListener(this);
        verificationBtn.addActionListener(this);

        adminLoginBtn.setFocusable(false);
        verificationBtn.setFocusable(false);

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
            CARD.add(new AdminWindow(this));
        } else if (e.getActionCommand().equals("VERIFICATION CODE")) {
            CARD.add(new ReadVerificationWindow(this));
        } else {
            for (int id = 0; id < MAX_ITEM; id++) {
                if (e.getActionCommand().equals(items[id].getItemName())) {
                    selectedItemId = id;
                    Item selectedItem = items[id];
                    System.out.println("selected " + selectedItem);
                    CARD.add(new ItemShowWindow(this, selectedItem));
                    break;
                }
            }
        }
    }
}
