package ui;

import domain.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import static ui.Window_1.selectedItemId;

public class Window_2 extends DvmWindow {
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
    private static final JButton add = new JButton("+");
    private static final JButton minus = new JButton("-");

//    private static final JLabel vmID = new JLabel("          VM's ID           ");

    private static JLabel itemQuantity;
    private static JLabel itemPrice;
    private static JLabel itemName;

    public Window_2(Controller controller) {
        super(controller);
    }

    protected void init() {
        selectedItemNum = 1;

        c = new GridBagConstraints();
        panel = new JPanel(new GridBagLayout());
        frame.add(panel);
        vmID.setBackground(Color.decode("#cfd0d1"));
        panel.setBackground(Color.decode("#dcebf7"));

        c.insets = new Insets(2, 10, 2, 2);
        vmID.setOpaque(true);
        panel.add(vmID, c);

        itemQuantity = new JLabel("1", SwingConstants.CENTER);
        itemQuantity.setText("1");
        setJLable(itemQuantity, btnItemQuantityWidth, btnItemQuantityHeight, true, Color.decode("#cfd0d1"), 1);

        itemName = new JLabel(items[selectedItemId].getItemName(), SwingConstants.CENTER);
        setJLable(itemName, btnItemNameWidth, btnItemNameHeight, true, Color.decode("#cfd0d1"), 1);

        itemPrice = new JLabel(Integer.toString(items[selectedItemId].getItemPrice()), SwingConstants.CENTER);
        setJLable(itemPrice, btnItemPriceWidth, btnItemPriceHeight, true, Color.decode("#cfd0d1"), 1);

        this.addComponent(btn1, 0, 0, 0, 15, 1, 1, 0.5, GridBagConstraints.CENTER);
        this.addComponent(btn2, 10, 2, 2, 10, 4, 0, 0.5, GridBagConstraints.FIRST_LINE_END);
        this.addComponent(add, 0, 0, 120, 180, 1, 1, 0.5, GridBagConstraints.CENTER);
        this.addComponent(minus, 0, 137, 120, 0, 1, 1, 0.5, GridBagConstraints.CENTER);
        this.addComponent(itemName, 0, 0, 250, 150, 1, 1, 0.5, GridBagConstraints.CENTER);
        this.addComponent(itemPrice, 0, 120, 250, 0, 1, 1, 0.5, GridBagConstraints.CENTER);
        this.addComponent(itemQuantity, 0, 0, 120, 20, 1, 1, 0.5, GridBagConstraints.CENTER);

        btn1.addActionListener(this);
        btn2.addActionListener(this);
        add.addActionListener(this);
        minus.addActionListener(this);

        btn1.setFocusable(false);
        btn2.setFocusable(false);
        add.setFocusable(false);
        minus.setFocusable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("NEXT")) {
            /* TODO: prove controller.selectItem() */
            String resMsg = controller.selectItem(selectedItemId, selectedItemNum, dvmInfo);
            System.out.println("result message: " + resMsg);
            System.out.println("dvmInfo: " + dvmInfo[0] + ", " + dvmInfo[1] + ", " + dvmInfo[1] + ", " + dvmInfo[2] + ", " + dvmInfo[3]);
            if (resMsg.equals("displayPayment")) {
                this.dispose();
                new Window_3_1(controller);
            } else if (resMsg.equals("displayPrepayment")) {
                this.dispose();
                new Window_3_2(controller);
            } else {
                /* TODO: display err dialog */
            }
        } else if (e.getActionCommand().equals("BACK")) {
            this.dispose();
            new Window_1(controller);
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

