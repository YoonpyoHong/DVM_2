package ui;

import domain.product.Item;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

import static domain.product.ItemManager.*;
import static ui.DvmWindow.*;

// Window8
public class AdminWindow extends DvmPanel {
    //random 7 drink list
    private static final Item[] localItems = new Item[MAX_LOCAL_ITEM];

    private JButton btn1;
    private JButton btn2;

    private static final JPanel itemLayout = new JPanel();
    private static final JPanel itemLayout2 = new JPanel();
    private static final JPanel itemLayout3 = new JPanel();

    private static final JTextField[] itemQty = new JTextField[MAX_LOCAL_ITEM];
    private static final EmptyBorder eb = new EmptyBorder(new Insets(20, 10, 0, 10));
    private static final Border grayLine = BorderFactory.createLineBorder(Color.decode("#cfd0d1"), 1);

    private static final int btnWidth = 120;
    private static final int btnHeight = 30;
    private static final int DRINK_PANEL_WIDTH = 350;
    private static final int DRINK_PANEL_HEIGHT = 400;
    private static final int drinkPanel2Width = 150;
    private static final int drinkPanel2Height = 250;
    private static final int drinkPanel3Width = 200;
    private static final int drinkPanel3Height = 250;

    public AdminWindow(DvmPanel prevPanel) {
        super(prevPanel);
        init();
    }

    @Override
    void init() {
        initLocalItems();
        initLayout();
    }

    @Override
    protected void initLayout() {
        super.initLayout();

        itemLayout.setPreferredSize(new Dimension(DRINK_PANEL_WIDTH, DRINK_PANEL_HEIGHT));
        itemLayout.setBorder(BorderFactory.createCompoundBorder(grayLine, eb));
        addComponent(mainPanel,itemLayout, 0, 6, 0, 10, 0, 1, 3, 10, GridBagConstraints.LINE_START);

        itemLayout2.setPreferredSize(new Dimension(drinkPanel2Width, drinkPanel2Height));
        addComponent(mainPanel,itemLayout2, 0, 0, 0, 125, 0, 0, 0, 0, GridBagConstraints.LINE_START);

        itemLayout3.setPreferredSize(new Dimension(drinkPanel3Width, drinkPanel3Height));
        addComponent(mainPanel,itemLayout3, 0, 15, 0, 0, 0, 0, 0, 0, GridBagConstraints.LINE_START);


        btn1 = new JButton("LOGOUT");
        btn1.setFocusable(false);
        btn1.addActionListener(this);
        addComponent(mainPanel,btn1, 10, 2, 2, 10, 4, 0, 0.5, GridBagConstraints.FIRST_LINE_END);

        btn2 = new JButton("UPDATE");
        btn2.setFocusable(false);
        btn2.addActionListener(this);
        addComponent(mainPanel,btn2, 10, 2, 2, 10, 4, 4, 0.5, GridBagConstraints.LINE_END);


        for (int i = 0; i < MAX_LOCAL_ITEM; i++) {
            itemQty[i] = new JTextField();
            itemQty[i].setPreferredSize(new Dimension(btnWidth + 50, btnHeight));
            itemQty[i].setDocument(new JTextFieldLimit(2));
            itemLayout3.add(itemQty[i], BorderLayout.CENTER);
        }

        for (int i = 0; i < MAX_LOCAL_ITEM; i++) {
            JLabel[] btn = new JLabel[MAX_LOCAL_ITEM];
            btn[i] = new JLabel(localItems[i].getItemName(), SwingConstants.CENTER);
            btn[i].setPreferredSize(new Dimension(btnWidth, btnHeight));
            btn[i].setOpaque(true);
            btn[i].setBackground(Color.WHITE);
            btn[i].setBorder(BorderFactory.createLineBorder(Color.decode("#cfd0d1"), 1));
            itemLayout2.add(btn[i], BorderLayout.CENTER);
        }
    }

    private void initLocalItems() {
        int cnt = 0;
        Item[] items = controller.getItemManager().getItemList();
        for (int i = 0; i < MAX_ITEM; i++) {
            if (items[i].getOnSale()) {
                localItems[cnt] = items[i];
                cnt += 1;
            }
        }
        assert cnt == MAX_LOCAL_ITEM;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("LOGOUT")) {
            resetCard();
            CARD_PANEL.add(new HomeWindow());
        } else if (e.getActionCommand().equals("UPDATE")) {
            boolean isValidInput = true;
            int[] inputQuantities = new int[MAX_LOCAL_ITEM];
            for (int i = 0; i < MAX_LOCAL_ITEM; i++) {
                String inputText = itemQty[i].getText();
                int inputNum;
                try {
                    inputNum = Integer.parseInt(inputText);
                    inputQuantities[i] = inputNum;
                } catch (NumberFormatException nfe) {
                    isValidInput = false;
                    break;
                }
                if (inputNum < 0 || inputNum > MAX_ITEM_QUANTITY) {
                    isValidInput = false;
                    break;
                }
            }
            if (isValidInput) {
                for (int i = 0; i < MAX_LOCAL_ITEM; i++) {
                    controller.getItemManager().updateQuantity(localItems[i].getItemId(), inputQuantities[i]);
                    itemQty[i].setText("");
                }
                controller.getItemManager().showItemList();
                // show JDialog (successful update)
                // for 15 second
            } else {
                String resMsg = "invalid input quantity";
                new DvmDialog(resMsg);
//				if there is a non-integer character,
//				show JDialog (Please input only integer);
            }
        }
    }
}
