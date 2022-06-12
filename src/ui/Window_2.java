package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import static ui.DvmWindow.*;
import static ui.Window_1.selectedItemId;

public class Window_2 extends JPanel implements ActionListener {
    private static final int MAX_ITEM_QUANTITY = 999;

    private static final int btnItemQuantityWidth = 100;
    private static final int btnItemQuantityHeight = 50;
    private static final int btnItemNameWidth = 100;
    private static final int btnItemNameHeight = 50;
    private static final int btnItemPriceWidth = 100;
    private static final int btnItemPriceHeight = 50;

    protected static int selectedItemNum;
    protected static int[] dvmInfo = new int[4];

    private static final JButton btn1 = new JButton("NEXT");
    private static final JButton btn2 = new JButton("BACK");
    JPanel panel;

    private static JLabel itemQuantity;
    private static JLabel itemPrice;

    public Window_2() {init();}

    protected void init() {
//        temporary item ID because items are null
        selectedItemId = 1;
        selectedItemNum = 1;
        c = new GridBagConstraints();
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.decode("#dcebf7"));
        addJLabel(panel);
        itemQuantity = new JLabel("1", SwingConstants.CENTER);
        itemQuantity.setText("1");
        setJLabel(itemQuantity, btnItemQuantityWidth, btnItemQuantityHeight, Color.decode("#cfd0d1"));
//        itemName = new JLabel(items[selectedItemId].getItemName(), SwingConstants.CENTER);
        JLabel itemName = new JLabel("Ice tea", SwingConstants.CENTER);
        setJLabel(itemName, btnItemNameWidth, btnItemNameHeight, Color.decode("#cfd0d1"));
        itemPrice = new JLabel("100", SwingConstants.CENTER);
//        itemPrice = new JLabel(Integer.toString(items[selectedItemId].getItemPrice()), SwingConstants.CENTER);
        setJLabel(itemPrice, btnItemPriceWidth, btnItemPriceHeight, Color.decode("#cfd0d1"));

//        current item's quantity based on dvm
        JLabel currentItemQty = new JLabel("<html><center>CURRENT STOCK OF [ITEM NAME] :<br>[ITEM CURRENT'S QUANTITY]");
//        initiate button here to remove action listener every time it changes panel
        JButton add = new JButton("+");
        JButton minus = new JButton("-");

        addComponent(panel, currentItemQty, 0, 0, 250, 0, 1, 1, 0.5, GridBagConstraints.CENTER);
        addComponent(panel,btn1, 100, 0, 0, 15, 1, 1, 0.5, GridBagConstraints.CENTER);
        addComponent(panel,btn2, 10, 2, 2, 10, 4, 0, 0.5, GridBagConstraints.FIRST_LINE_END);
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
            String resMsg = controller.selectItem(selectedItemId, selectedItemNum, dvmInfo);
            System.out.println("result message: " + resMsg);
            System.out.println("dvmInfo: " + dvmInfo[0] + ", " + dvmInfo[1] + ", " + dvmInfo[1] + ", " + dvmInfo[2] + ", " + dvmInfo[3]);
            if (resMsg.equals("displayPayment")) {
                CARD.removeAll();
                CARD.revalidate();
                CARD.repaint();
                CARD.add(new Window_3_1());
            } else if (resMsg.equals("displayPrepayment")) {
                CARD.removeAll();
                CARD.revalidate();
                CARD.repaint();
                CARD.add(new Window_3_2());
            } else {
                /* TODO: display err dialog */
            }
        } else if (e.getActionCommand().equals("BACK")) {
            CARD.removeAll();
            CARD.revalidate();
            CARD.repaint();
            CARD.add(new Window_1());
        } else if (e.getActionCommand().equals("+")) {
            selectedItemNum = Math.min(selectedItemNum + 1, MAX_ITEM_QUANTITY);
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

