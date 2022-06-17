package ui;

import domain.product.Item;
import domain.product.ItemManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static ui.DvmWindow.*;

// Window2
public class ItemShowWindow extends DvmPanel {
    private static final int btnItemQuantityWidth = 100;
    private static final int btnItemQuantityHeight = 50;
    private static final int btnItemNameWidth = 100;
    private static final int btnItemNameHeight = 50;
    private static final int btnItemPriceWidth = 100;
    private static final int btnItemPriceHeight = 50;

    private JButton btn1;
    private JButton btn2;
    JPanel panel;

    private static JLabel itemQuantity;
    private static JLabel itemPrice;

    private static final int DEFAULT_ITEM_QUANTITY = 1;

    protected int selectedItemNum = DEFAULT_ITEM_QUANTITY;
    protected static int[] dvmInfo = new int[4];

    public ItemShowWindow(DvmPanel prevPanel, Item selectedItem) {
        super(prevPanel, selectedItem);
    }

    protected void init() {
        super.init();
        constraints = new GridBagConstraints();
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.decode("#dcebf7"));
        addJLabel(panel);
        itemQuantity = new JLabel(Integer.toString(selectedItemNum), SwingConstants.CENTER);
        setJLabel(itemQuantity, btnItemQuantityWidth, btnItemQuantityHeight, Color.decode("#cfd0d1"));
        JLabel itemName = new JLabel(selectedItem.getItemName(), SwingConstants.CENTER);
        setJLabel(itemName, btnItemNameWidth, btnItemNameHeight, Color.decode("#cfd0d1"));
        itemPrice = new JLabel(Integer.toString(selectedItem.getItemPrice()), SwingConstants.CENTER);
        setJLabel(itemPrice, btnItemPriceWidth, btnItemPriceHeight, Color.decode("#cfd0d1"));

//        current item's quantity based on dvm
        JLabel currentItemQty = new JLabel(String.format("<html><center>%s: %s 개", selectedItem.getItemName(), selectedItem.getItemQuantity()));
//        initiate button here to remove action listener every time it changes panel
        JButton add = new JButton("+");
        JButton minus = new JButton("-");

        btn1 = new JButton("NEXT");
        btn2 = new JButton("BACK");
        addComponent(panel, currentItemQty, 0, 0, 250, 0, 1, 1, 0.5, GridBagConstraints.CENTER);
        addComponent(panel, btn1, 100, 0, 0, 15, 1, 1, 0.5, GridBagConstraints.CENTER);
        addComponent(panel, btn2, 10, 2, 2, 10, 4, 0, 0.5, GridBagConstraints.FIRST_LINE_END);
        addComponent(panel,add, 0, 0, 0, 180, 1, 1, 0.5, GridBagConstraints.CENTER);
        addComponent(panel,minus, 0, 137, 0, 0, 1, 1, 0.5, GridBagConstraints.CENTER);
        addComponent(panel,itemName, 0, 0, 120, 150, 1, 1, 0.5, GridBagConstraints.CENTER);
        addComponent(panel,itemPrice, 0, 120, 120, 0, 1, 1, 0.5, GridBagConstraints.CENTER);
        addComponent(panel,itemQuantity, 0, 0, 0, 20, 1, 1, 0.5, GridBagConstraints.CENTER);

        btn1.addActionListener(this);
        btn2.addActionListener(this);
        add.addActionListener(this);
        minus.addActionListener(this);

        btn1.setFocusable(false);
        btn2.setFocusable(false);
        add.setFocusable(false);
        minus.setFocusable(false);

        CARD.add(panel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("NEXT")) {
            /* TODO: prove controller.selectItem() */
            String resMsg = controller.selectItem(selectedItem.getItemId(), selectedItemNum, dvmInfo);
            System.out.println(String.format("%s: result message: %s", this.getClass().toString() + "actionPerformed", resMsg));
            System.out.println(String.format("%s: dvmInfo: %s", this.getClass().toString() + "actionPerformed", dvmInfo[0] + ", " + dvmInfo[1] + ", " + dvmInfo[1] + ", " + dvmInfo[2] + ", " + dvmInfo[3]));
            if (resMsg.equals("displayPayment")) {
                resetCard();
                CARD.add(new PaymentWindow(this));
            } else if (resMsg.equals("displayPrepayment")) {
                resetCard();
                CARD.add(new PrepaymentWindow(this));
            } else {
                 /* TODO: display err dialog */
            }
        } else if (e.getActionCommand().equals("BACK")) {
            resetCard();
            prevPanel.init();
            CARD.add(prevPanel);
        } else if (e.getActionCommand().equals("+")) {
            selectedItemNum = Math.min(selectedItemNum + 1, ItemManager.MAX_ITEM_QUANTITY);
            System.out.println("selectedItemNum = " + selectedItemNum);
            itemQuantity.setText(Integer.toString(selectedItemNum));
        } else if (e.getActionCommand().equals("-")) {
            selectedItemNum = Math.max(selectedItemNum - 1, 1);
            System.out.println("selectedItemNum = " + selectedItemNum);
            itemQuantity.setText(Integer.toString(selectedItemNum));
        }
    }

    protected static String getTotalPrice() {
        int totalPrice = Integer.parseInt(itemQuantity.getText()) * Integer.parseInt(itemPrice.getText());
        return Integer.toString(totalPrice);
    }
}
