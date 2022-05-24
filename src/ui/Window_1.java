package ui;

import domain.app.Controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import static domain.product.ItemManager.MAX_ITEM;

public class Window_1 extends DvmWindow {
    public static int selectedItemId;
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

    public Window_1(Controller controller) {
        super(controller);
    }

    @Override
    protected void init() {
        // set drink list layout's size
        itemLayout.setPreferredSize(new Dimension(drinkPanelWidth, drinkPanelHeight));
        // set border + margin
        itemLayout.setBorder(BorderFactory.createCompoundBorder(grayLine, eb));

        c = new GridBagConstraints();

        panel = new JPanel(new GridBagLayout());
        frame.add(panel);
        //set color by hex code
        vmID.setBackground(Color.decode("#cfd0d1"));
        panel.setBackground(Color.decode("#dcebf7"));
        itemLayout.setBackground(Color.decode("#ebeced"));

        addJLable(2, 10, 2, 2, true, vmID);

        addComponent(10, 2, 2, 10, 4, 0, 0.5, GridBagConstraints.FIRST_LINE_END, btn1);
        addComponent(10, 2, 2, 10, 4, 4, 0.5, GridBagConstraints.LINE_END, btn2);

        btn1.addActionListener(this);
        btn2.addActionListener(this);

        //remove border around text inside the button
        btn1.setFocusable(false);
        btn2.setFocusable(false);

        for (int i = 0; i < MAX_ITEM; i++) {
            JButton[] btn = new JButton[MAX_ITEM];
            btn[i] = new JButton(items[i].getItemName());
            btn[i].setFocusable(false);
            btn[i].setPreferredSize(new Dimension(btnWidth, btnHeight));
            itemLayout.add(btn[i]);
            btn[i].addActionListener(this);
//            System.err.println("init btn[" + i + "]");
        }

        addComponent(0, 9, 0, 10, 0, 1,3, 10, GridBagConstraints.PAGE_START, itemLayout);
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
            for (int id = 0; id < MAX_ITEM; id++) {
                if (e.getActionCommand().equals(items[id].getItemName())) {
                    System.err.println("selected " + items[id]);
                    selectedItemId = id;
                    this.dispose();
                    new Window_2(controller);
                    break;
                }
            }
        }
    }
}
