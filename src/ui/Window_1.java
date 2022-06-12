package ui;

import static domain.product.ItemManager.MAX_ITEM;
import static ui.DvmWindow.*;
import static ui.DvmWindow.items;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class Window_1 extends JPanel implements ActionListener {
    public static int selectedItemId;
    private static final JButton btn1 = new JButton("ADMIN LOGIN");
    private static final JButton btn2 = new JButton("VERIFICATION CODE");

    private static final EmptyBorder eb = new EmptyBorder(new Insets(20, 10, 0, 10));
    private static final Border grayLine = BorderFactory.createLineBorder(Color.decode("#cfd0d1"), 1);

    private static final int btnWidth = 120;
    private static final int btnHeight = 30;
    private static final int drinkPanelWidth = 290;
    private static final int drinkPanelHeight = 400;

    public Window_1() {
        init();
    }

    protected void init() {
        JPanel panel = new JPanel(new GridBagLayout());
        JPanel itemLayout = new JPanel();

        itemLayout.setPreferredSize(new Dimension(drinkPanelWidth, drinkPanelHeight));
        itemLayout.setBorder(BorderFactory.createCompoundBorder(grayLine, eb));

        c = new GridBagConstraints();
        panel.setBackground(Color.decode("#dcebf7"));
        itemLayout.setBackground(Color.decode("#ebeced"));

        addJLabel(panel);
        addComponent(panel,btn1, 10, 2, 2, 10, 4, 0, 0.5, GridBagConstraints.FIRST_LINE_END);
        addComponent(panel, btn2, 10, 2, 2, 10, 4, 4, 0.5, GridBagConstraints.LINE_END);

        btn1.addActionListener(this);
        btn2.addActionListener(this);

        btn1.setFocusable(false);
        btn2.setFocusable(false);

        for (int i = 0; i < MAX_ITEM; i++) {
            JButton[] btn = new JButton[MAX_ITEM];
//            temporary replacement:
            btn[i] = new JButton("TEST");
//            error: null ptr exception on items[i].getItemName()
//            items are null
//            btn[i] = new JButton(items[i].getItemName());
            btn[i].setFocusable(false);
            btn[i].setPreferredSize(new Dimension(btnWidth, btnHeight));
            itemLayout.add(btn[i]);
            btn[i].addActionListener(this);
//            System.out.println("init btn[" + i + "]");
        }

        addComponent(panel, itemLayout, 0, 9, 0, 10, 0, 1,3, 10);
        itemLayout.setMinimumSize(new Dimension(280,390));
        CARD.add(panel);
    }

    public void actionPerformed(ActionEvent e) {
        CARD.removeAll();
        CARD.revalidate();
        CARD.repaint();

        if (e.getActionCommand().equals("ADMIN LOGIN")) {
            CARD.add(new Window_8());
        } else if (e.getActionCommand().equals("VERIFICATION CODE")) {
            CARD.add(new Window_6());
        } else {
            for (int id = 0; id < MAX_ITEM; id++) {
                if (e.getActionCommand().equals(items[id].getItemName())
                || e.getActionCommand().equals("TEST")) {
                    System.out.println("selected " + items[id]);
                    selectedItemId = id;
                    CARD.add(new Window_2());
                    break;
                }
            }
        }
    }
}
