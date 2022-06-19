package ui;

import domain.payment.Order;
import domain.product.Item;
import domain.product.ItemManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static ui.DvmWindow.*;

// Window2
public class ItemShowWindow extends DvmPanel {
    private static final int ITEM_QUANTITY_BUTTON_WIDTH = 100;
    private static final int ITEM_QUANTITY_BUTTON_HEIGHT = 50;
    private static final int ITEM_NAME_BUTTON_WIDTH = 100;
    private static final int ITEM_NAME_BUTTON_HEIGHT = 50;
    private static final int ITEM_PRICE_BUTTON_WIDTH = 100;
    private static final int ITEM_PRICE_BUTTON_HEIGHT = 50;
    private static final int DEFAULT_ITEM_QUANTITY = 1;

    private static JLabel itemQuantityLabel;
    private static JLabel itemPriceLabel;

    private int selectedItemNum = DEFAULT_ITEM_QUANTITY;
    private final Item selectedItem;

    protected static int[] dvmInfo = new int[4];

    public ItemShowWindow(DvmPanel prevPanel, Item selectedItem) {
        super(prevPanel);
        this.selectedItem = selectedItem;
        init();
    }


    @Override
    void init() {
        initLayout();
    }

    @Override
    protected void initLayout() {
        super.initLayout();

        itemQuantityLabel = new JLabel(Integer.toString(selectedItemNum), SwingConstants.CENTER);
        setJLabel(itemQuantityLabel, ITEM_QUANTITY_BUTTON_WIDTH, ITEM_QUANTITY_BUTTON_HEIGHT, Color.decode("#cfd0d1"));
        addComponent(mainPanel, itemQuantityLabel, 0, 0, 0, 20, 1, 1, 0.5, GridBagConstraints.CENTER);

        JLabel itemName = new JLabel(selectedItem.getItemName(), SwingConstants.CENTER);
        setJLabel(itemName, ITEM_NAME_BUTTON_WIDTH, ITEM_NAME_BUTTON_HEIGHT, Color.decode("#cfd0d1"));
        addComponent(mainPanel,itemName, 0, 0, 120, 150, 1, 1, 0.5, GridBagConstraints.CENTER);

        itemPriceLabel = new JLabel(Integer.toString(selectedItem.getItemPrice()), SwingConstants.CENTER);
        setJLabel(itemPriceLabel, ITEM_PRICE_BUTTON_WIDTH, ITEM_PRICE_BUTTON_HEIGHT, Color.decode("#cfd0d1"));
        addComponent(mainPanel, itemPriceLabel, 0, 120, 120, 0, 1, 1, 0.5, GridBagConstraints.CENTER);

//        current item's quantity based on dvm
        JLabel currentItemQty = new JLabel(String.format("<html><center>%s: %s 개", selectedItem.getItemName(), selectedItem.getItemQuantity()));
        addComponent(mainPanel, currentItemQty, 0, 0, 250, 0, 1, 1, 0.5, GridBagConstraints.CENTER);
//        initiate button here to remove action listener every time it changes mainPanel

        JButton add = new JButton("+");
        add.addActionListener(this);
        add.setFocusable(false);
        addComponent(mainPanel,add, 0, 0, 0, 180, 1, 1, 0.5, GridBagConstraints.CENTER);

        JButton minus = new JButton("-");
        minus.addActionListener(this);
        minus.setFocusable(false);
        addComponent(mainPanel,minus, 0, 137, 0, 0, 1, 1, 0.5, GridBagConstraints.CENTER);

        JButton btn1 = new JButton("NEXT");
        btn1.addActionListener(this);
        btn1.setFocusable(false);
        addComponent(mainPanel, btn1, 100, 0, 0, 15, 1, 1, 0.5, GridBagConstraints.CENTER);

        JButton btn2 = new JButton("BACK");
        btn2.addActionListener(this);
        btn2.setFocusable(false);
        addComponent(mainPanel, btn2, 10, 2, 2, 10, 4, 0, 0.5, GridBagConstraints.FIRST_LINE_END);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("NEXT")) {
            /* TODO: prove controller.selectItem() */
            String resMsg = controller.selectItem(selectedItem.getItemId() - 1, selectedItemNum, dvmInfo);
            System.out.printf("%s(): result message: %s%n", this.getClass().toString() + " actionPerformed", resMsg);
            System.out.printf("%s(): dvmInfo: %s%n", this.getClass() + " actionPerformed", dvmInfo[0] + ", " + dvmInfo[1] + ", " + dvmInfo[1] + ", " + dvmInfo[2] + ", " + dvmInfo[3]);
            Order order = new Order(selectedItem, selectedItemNum);
            if (resMsg.equals("displayPayment")) {
                resetCard();
                CARD_PANEL.add(new PaymentWindow(this, order));
            } else if (resMsg.equals("displayPrepayment")) {
                resetCard();
                CARD_PANEL.add(new PrepaymentWindow(this, order));
            } else {
                new DvmDialog(resMsg);
            }
        } else if (e.getActionCommand().equals("BACK")) {
            resetCard();
            prevPanel.init();
            CARD_PANEL.add(prevPanel);
        } else if (e.getActionCommand().equals("+")) {
            selectedItemNum = Math.min(selectedItemNum + 1, ItemManager.MAX_ITEM_QUANTITY);
            System.out.println("selectedItemNum = " + selectedItemNum);
            itemQuantityLabel.setText(Integer.toString(selectedItemNum));
        } else if (e.getActionCommand().equals("-")) {
            selectedItemNum = Math.max(selectedItemNum - 1, 1);
            System.out.println("selectedItemNum = " + selectedItemNum);
            itemQuantityLabel.setText(Integer.toString(selectedItemNum));
        }
    }

    protected static String getTotalPrice() {
        int totalPrice = Integer.parseInt(itemQuantityLabel.getText()) * Integer.parseInt(itemPriceLabel.getText());
        return Integer.toString(totalPrice);
    }
}
