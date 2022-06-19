package ui;

import domain.product.Item;
import domain.product.ItemManager;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

import static domain.product.ItemManager.*;
import static ui.DvmWindow.*;

// Window8
public class AdminWindow extends DvmPanel {
    private static final int BUTTON_WIDTH = 120;
    private static final int BUTTON_HEIGHT = 30;
    private static final int TEXT_FIELD_WIDTH = BUTTON_WIDTH + 50;
    private static final int TEXT_FIELD_HEIGHT = BUTTON_HEIGHT;


    private static final int DRINK_PANEL_WIDTH = 350;
    private static final int DRINK_PANEL_HEIGHT = 400;

    private static final int DRINK_PANEL2_WIDTH = 100;
    private static final int DRINK_PANEL2_HEIGHT = 250;

    private static final int DRINK_PANEL3_WIDTH = 200;
    private static final int DRINK_PANEL3_HEIGHT = 250;

    private static final Item[] localItems = new Item[MAX_LOCAL_ITEM];

    private final JTextField[] itemQtyTextField = new JTextField[MAX_LOCAL_ITEM];
    private final EmptyBorder emptyBorder = new EmptyBorder(new Insets(20, 10, 0, 10));
    private final Border grayLineBorder = BorderFactory.createLineBorder(Color.decode("#cfd0d1"), 1);

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

        JPanel itemLayout = new JPanel();
        itemLayout.setPreferredSize(new Dimension(DRINK_PANEL_WIDTH, DRINK_PANEL_HEIGHT));
        itemLayout.setBorder(BorderFactory.createCompoundBorder(grayLineBorder, emptyBorder));
        addComponent(mainPanel,itemLayout, 0, 6, 0, 10, 0, 1, 3, 10, GridBagConstraints.PAGE_START);

        JPanel itemLayout2 = new JPanel();
        itemLayout2.setPreferredSize(new Dimension(DRINK_PANEL2_WIDTH, DRINK_PANEL2_HEIGHT));
        addComponent_test(itemLayout,itemLayout2);
//            addComponent(mainPanel,itemLayout2, 0, 0, 0, 125, 0, 0, 0, 0, GridBagConstraints.LINE_END);

        JPanel itemLayout3 = new JPanel();
        itemLayout3.setPreferredSize(new Dimension(DRINK_PANEL3_WIDTH, DRINK_PANEL3_HEIGHT));
        addComponent_test(itemLayout,itemLayout3);
//        addComponent(mainPanel,itemLayout3,0, 15, 0, 0, 0, 0, 0, 0, GridBagConstraints.LINE_START);

        JButton logoutBtn = new JButton("LOGOUT");
        logoutBtn.setFocusable(false);
        logoutBtn.addActionListener(this);
        addComponent(mainPanel, logoutBtn, 10, 2, 2, 10, 4, 0, 0.5, GridBagConstraints.FIRST_LINE_END);

        JButton updateBtn = new JButton("UPDATE");
        updateBtn.setFocusable(false);
        updateBtn.addActionListener(this);
        addComponent(mainPanel, updateBtn, 10, 2, 2, 10, 4, 4, 0.5, GridBagConstraints.LINE_END);

        initTextFields(itemLayout3);
        initDrinkList(itemLayout2);
    }

    private  void addComponent_test(JPanel panel, Component component){
        panel.add(component, BorderLayout.CENTER);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("LOGOUT")) {
            resetCard();
            CARD_PANEL.add(new HomeWindow());
        } else if (e.getActionCommand().equals("UPDATE")) {
            boolean isValidInput = true;
            int[] inputQuantities = new int[MAX_LOCAL_ITEM];
            for (int i = 0; i < MAX_LOCAL_ITEM; i++) {
                String inputText = itemQtyTextField[i].getText();
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
                    localItems[i].setItemQuantity(inputQuantities[i]);
                    itemQtyTextField[i].setText("");
                }
                controller.getItemManager().showItemList();
                String resMsg = "update success!";
                new DvmDialog(resMsg);
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

    private void initDrinkList(JPanel panel) {
        for (int i = 0; i < ItemManager.MAX_LOCAL_ITEM; i++) {
            JLabel[] drinkName = new JLabel[ItemManager.MAX_LOCAL_ITEM];
            drinkName[i] = new JLabel(localItems[i].getItemName(), SwingConstants.CENTER);
            drinkName[i].setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
            drinkName[i].setOpaque(true);
            drinkName[i].setBackground(Color.WHITE);
            drinkName[i].setBorder(BorderFactory.createLineBorder(Color.decode("#cfd0d1"), 1));
            panel.add(drinkName[i], BorderLayout.CENTER);
        }
    }

    private void initTextFields(JPanel panel) {
        for (int i = 0; i < MAX_LOCAL_ITEM; i++) {
            itemQtyTextField[i] = new JTextField();
            itemQtyTextField[i].setPreferredSize(new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT));
            itemQtyTextField[i].setDocument(new DvmJTextFieldLimit(2));
            panel.add(itemQtyTextField[i], BorderLayout.CENTER);
        }
    }

    private void initLocalItems() {
        int localItemCnt = 0;
        Item[] items = controller.getItemManager().getItemList();
        for (int i = 0; i < MAX_ITEM; i++) {
            if (items[i].getOnSale()) {
                localItems[localItemCnt] = items[i];
                localItemCnt += 1;
            }
        }
    }
}
