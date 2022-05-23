package ui3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class Window_8 extends DvmWindow {
    //random 7 drink list
    private static final String[] itemList = new String[MAX_LOCAL_ITEM];

    private static final JButton btn1 = new JButton("LOGOUT");
    private static final JButton btn2 = new JButton("UPDATE");

    private static final JPanel itemLayout = new JPanel();
    private static final JPanel itemLayout2 = new JPanel();
    private static final JPanel itemLayout3 = new JPanel();

    private static final JTextField[] itemQty = new JTextField[MAX_LOCAL_ITEM];

    //padding for top, left, bottom, right
    private static final EmptyBorder eb = new EmptyBorder(new Insets(20, 10, 0, 10));
    private static final Border grayLine = BorderFactory.createLineBorder(Color.decode("#cfd0d1"), 1);

    private static final int btnWidth = 120;
    private static final int btnHeight = 30;
    private static final int drinkPanelWidth = 350;
    private static final int drinkPanelHeight = 400;
    private static final int drinkPanel2Width = 150;
    private static final int drinkPanel2Height = 250;
    private static final int drinkPanel3Width = 200;
    private static final int drinkPanel3Height = 250;
    private static final int[] rand = genRandom();

    public Window_8(Controller controller) {
        super(controller);
    }

    protected void init() {

//      ===============================================================
//      		c, the layout constraint is initiated here
        
        c = new GridBagConstraints();
        
//      ===============================================================  

        panel = new JPanel(new GridBagLayout());
        //generate MAX_LOCAL_ITEM item list randomly for every dvm
        for (int i = 0; i < rand.length; i++) {
            itemList[i] = drinkList[rand[i]];
        }

        //set drink list layout's size
        itemLayout.setPreferredSize(new Dimension(drinkPanelWidth, drinkPanelHeight));
        itemLayout2.setPreferredSize(new Dimension(drinkPanel2Width, drinkPanel2Height));
        itemLayout3.setPreferredSize(new Dimension(drinkPanel3Width, drinkPanel3Height));

        //set color by hex code
        vmID.setBackground(Color.decode("#cfd0d1"));
        panel.setBackground(Color.decode("#dcebf7"));

        //set border + margin
        itemLayout.setBorder(BorderFactory.createCompoundBorder(grayLine, eb));

        //add panel to frame
        frame.add(panel);

        //padding for top, left, bottom, right
        c.insets = new Insets(2, 10, 2, 2);

        vmID.setOpaque(true);
        panel.add(vmID, c);

        //padding for top, left, bottom, right
        c.insets = new Insets(10, 2, 2, 10);
        c.anchor = GridBagConstraints.FIRST_LINE_END; //top corner right
        c.weightx = 0.5;
        c.gridx = 4;
        c.gridy = 0;
        panel.add(btn1, c);

        c.gridx = 4;
        c.gridy = 4;
        c.anchor = GridBagConstraints.LINE_END; //center right
        c.weighty = 0.5;
        panel.add(btn2, c);

        btn1.addActionListener(this);
        btn2.addActionListener(this);

        //remove border around text inside the button
        btn1.setFocusable(false);
        btn2.setFocusable(false);

        for (int i = 0; i < MAX_LOCAL_ITEM; i++) {
            itemQty[i] = new JTextField();
            itemQty[i].setPreferredSize(new Dimension(btnWidth + 50, btnHeight));
            itemQty[i].setDocument(new JTextFieldLimit(2));
            itemLayout3.add(itemQty[i], BorderLayout.CENTER);
        }

        for (int i = 0; i < MAX_LOCAL_ITEM; i++) {
            JLabel[] btn = new JLabel[MAX_LOCAL_ITEM];
            btn[i] = new JLabel(itemList[i], SwingConstants.CENTER);
            btn[i].setPreferredSize(new Dimension(btnWidth, btnHeight));
            btn[i].setOpaque(true);
            btn[i].setBackground(Color.WHITE);
            btn[i].setBorder(BorderFactory.createLineBorder(Color.decode("#cfd0d1"), 1));
            itemLayout2.add(btn[i], BorderLayout.CENTER);
        }

        c.anchor = GridBagConstraints.LINE_START; //right
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 15, 0, 0);
        c.gridwidth = 0;
        c.gridheight = 0;
        panel.add(itemLayout3, c);

        c.anchor = GridBagConstraints.LINE_END; //right
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 0, 125);
        c.gridwidth = 0;
        c.gridheight = 0;
        panel.add(itemLayout2, c);

        c.anchor = GridBagConstraints.PAGE_START; //center left
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0, 6, 0, 10);
        c.gridwidth = 3;
        c.gridheight = 10;
        panel.add(itemLayout, c);

    }

    private static int[] genRandom() {
        // generate random number from 0 to 20
        Random rand = new Random();
        int[] temp = new int[MAX_LOCAL_ITEM];

        for (int i = 0; i < temp.length; i++) {
            temp[i] = rand.nextInt(MAX_ITEM); //0~19
            for (int j = 0; j < i; j++) {
                if (temp[i] == temp[j]) {
                    i--; //no repetition of same number
                    break;
                }
            }
        }
        return temp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("LOGOUT")) {
            //do nothing
            this.dispose();new Window_1(controller);
            
        } else if (e.getActionCommand().equals("UPDATE")) {
//			below is unimplemented code:
//			i don't know if it will work,
//			but the idea is to get the item's name
//			every time admin click the button update
//			and update its quantity in the local storage

            for (int i = 0; i < itemList.length; i++) {
                itemQty[i].getText();

                System.out.println(itemQty[i].getText());
//				if there is a non-integer character,
//				show JDialog (Please input only integer);

//				clear text field
                itemQty[i].setText("");
            }
            
//			update data //
//			show JDialog (successful update)
//			for 15 second

        }
    }
}