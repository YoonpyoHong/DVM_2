package ui3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Window_2 extends DvmWindow {
    private static final int MAX_ITEM_QUANTITY = 999;
    private static final int btnItemQuantityWidth = 100;
    private static final int btnItemQuantityHeight = 50;
    private static final int btnItemNameWidth = 100;
    private static final int btnItemNameHeight = 50;
    private static final int btnItemPriceWidth = 100;
    private static final int btnItemPriceHeight = 50;

    private static final JButton btn1 = new JButton("NEXT");
    private static final JButton btn2 = new JButton("BACK");
    private static final JButton add = new JButton("+");
    private static final JButton minus = new JButton("-");

    private static final JLabel vmID = new JLabel("          VM's ID           ");

    private static final Integer[] num = new Integer[MAX_ITEM_QUANTITY];
    private static final JLabel itemQuantity = new JLabel("1", SwingConstants.CENTER);
    private static final JLabel itemPrice = new JLabel(Window_1.getItemPrice(), SwingConstants.CENTER);

    private static int itemNum;

    public Window_2(Controller controller) {
        super(controller);
    }

    @Override
    protected void init() {
        panel = new JPanel(new GridBagLayout());

        
//      ===============================================================
//      		c, the layout constraint is initiated here
        
        c = new GridBagConstraints();
        
//      ===============================================================
        
        itemNum = 1;
        itemQuantity.setText("1");

        for (int i = 0; i < MAX_ITEM_QUANTITY; i++) {
            num[i] = i;
        }
        frame.add(panel);
        vmID.setBackground(Color.decode("#cfd0d1"));
        panel.setBackground(Color.decode("#dcebf7"));

        //padding for top, left, bottom, right
        c.insets = new Insets(2, 10, 2, 2);
        vmID.setOpaque(true);
        panel.add(vmID, c);

        itemQuantity.setPreferredSize(new Dimension(btnItemQuantityWidth, btnItemQuantityHeight));
        itemQuantity.setOpaque(true);
        itemQuantity.setBorder(BorderFactory.createLineBorder(Color.decode("#cfd0d1"), 1));

        JLabel itemName = new JLabel(Window_1.getItemName(), SwingConstants.CENTER);
        itemName.setPreferredSize(new Dimension(btnItemNameWidth, btnItemNameHeight));
        itemName.setOpaque(true);
        itemName.setBorder(BorderFactory.createLineBorder(Color.decode("#cfd0d1"), 1));

        itemPrice.setPreferredSize(new Dimension(btnItemPriceWidth, btnItemPriceHeight));
        itemPrice.setOpaque(true);
        itemPrice.setBorder(BorderFactory.createLineBorder(Color.decode("#cfd0d1"), 1));

        c.insets = new Insets(10, 2, 2, 10);
        c.anchor = GridBagConstraints.FIRST_LINE_END; //top corner right
        c.weightx = 0.5;
        c.gridx = 4;
        c.gridy = 0;
        panel.add(btn2, c);

        c.insets = new Insets(0, 0, 250, 150);
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER; //center
        c.weighty = 0.5;
        panel.add(itemName, c);

        c.insets = new Insets(0, 120, 250, 0);
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER; //center
        c.weighty = 0.5;
        panel.add(itemPrice, c);

        c.insets = new Insets(0, 0, 120, 180);
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER; //center
        c.weighty = 0.5;
        panel.add(add, c);

        c.insets = new Insets(0, 0, 120, 20);
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER; //center
        c.weighty = 0.5;
        panel.add(itemQuantity, c);

        c.insets = new Insets(0, 137, 120, 0);
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER; //center
        c.weighty = 0.5;
        panel.add(minus, c);

        c.insets = new Insets(0, 0, 0, 15);
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER; //center
        c.weighty = 0.5;
        panel.add(btn1, c);

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
            this.dispose();
//            new Window_3_1(controller);
            
//			if prepayment:
				new Window_3_2(controller);
            
            
        } else if (e.getActionCommand().equals("BACK")) {
            this.dispose();new Window_1(controller);
        } else if (e.getActionCommand().equals("+")) {
            itemNum = Math.min(itemNum + 1, MAX_ITEM_QUANTITY);
            itemQuantity.setText(Integer.toString(itemNum));
        } else if (e.getActionCommand().equals("-")) {
            itemNum = Math.max(itemNum - 1, 0);
            itemQuantity.setText(Integer.toString(itemNum));
        }
    }

    protected static String getTotalPrice() {
        int totalPrice = Integer.parseInt(itemQuantity.getText()) * Integer.parseInt(itemPrice.getText());
        return Integer.toString(totalPrice);
    }

}
