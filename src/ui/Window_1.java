package ui;

import domain.app.Controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import static domain.product.ItemManager.MAX_ITEM;

public class Window_1 extends DvmWindow {
    private static final JPanel itemLayout = new JPanel();

    private static final JButton btn1 = new JButton("ADMIN LOGIN");
    private static final JButton btn2 = new JButton("VERIFICATION CODE");

    //padding for top, left, bottom, right
    private static final EmptyBorder eb = new EmptyBorder(new Insets(20, 10, 0, 10));
    private static final Border grayLine = BorderFactory.createLineBorder(Color.decode("#cfd0d1"), 1);

    private static final int btnWidth = 120;
    private static final int btnHeight = 30;
    private static final int drinkPanelWidth = 290;
    private static final int drinkPanelHeight = 400;

    protected static int selectedItemId = -1;

    public Window_1(Controller controller) {
        super(controller);
    }

    @Override
    protected void init() {
        //set drink list layout's size
        itemLayout.setPreferredSize(new Dimension(drinkPanelWidth, drinkPanelHeight));

        panel = new JPanel(new GridBagLayout());
        c = new GridBagConstraints();
        //set color by hex code
        vmID.setBackground(Color.decode("#cfd0d1"));
        panel.setBackground(Color.decode("#dcebf7"));
        itemLayout.setBackground(Color.decode("#ebeced"));

        //set border + margin
        itemLayout.setBorder(BorderFactory.createCompoundBorder(grayLine, eb));

        //padding for top, left, bottom, right
        c.insets = new Insets(2, 10, 2, 2);

        frame.add(panel);
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

//        ====================================================================
//        If condition to not run the loop if i == 20

        if (DvmWindow.i != MAX_ITEM) {
            for (int j = 0; j < MAX_ITEM; j++) {
                JButton[] btn = new JButton[MAX_ITEM];
                btn[j] = new JButton(items[j].getItemName());
                btn[j].setFocusable(false);
                btn[j].setPreferredSize(new Dimension(btnWidth, btnHeight));
                itemLayout.add(btn[j]);
                btn[j].addActionListener(this);
                ++i;
            }
        }

        c.anchor = GridBagConstraints.PAGE_START; //center left
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0, 9, 0, 10);
        c.gridwidth = 3;
        c.gridheight = 10;
        panel.add(itemLayout, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ADMIN LOGIN")) {
            this.dispose();
            new Window_7(controller);
        } else if (e.getActionCommand().equals("VERIFICATION CODE")) {
            this.dispose();
            new Window_6(controller);
        } else {
            for (int i = 0; i < MAX_ITEM; i++) {
                if (e.getActionCommand().equals(items[i].getItemName())) {
                    System.err.println("Selected " + items[i]);
                    selectedItemId = i;
                    this.dispose();
                    new Window_2(controller);
                    break;
                }
            }
        }
    }
}
