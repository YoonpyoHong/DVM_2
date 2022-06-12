package ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import static ui.DvmWindow.*;

public class Window_3_1 extends JPanel implements ActionListener {
    private static final JButton btn1 = new JButton("PAY");
    private static final JButton btn2 = new JButton("BACK");
    private static final JLabel totalPrice = new JLabel("<html>Total price:<br><center>" + Window_2.getTotalPrice() + "</center></html>", SwingConstants.CENTER);
    private static final int btnTotalPriceWidth = 100;
    private static final int btnTotalPriceHeight = 70;

    JPanel panel;

    public Window_3_1() {
        init();
    }

    protected void init() {
        panel = new JPanel(new GridBagLayout());
        c = new GridBagConstraints();
        CARD.add(panel);
        panel.setBackground(Color.decode("#dcebf7"));

        addJLabel(panel);
        setJLabel(totalPrice, btnTotalPriceWidth, btnTotalPriceHeight, Color.decode("#cfd0d1"));

        addComponent(panel,btn1, 0, 55, 0, 0, 1, 1, 0.5, GridBagConstraints.CENTER);
        addComponent(panel,btn2, 10, 2, 2, 10, 4, 0, 0.5, GridBagConstraints.FIRST_LINE_END);
        addComponent(panel,totalPrice, 0, 50, 120, 0, 1, 1, 0.5, GridBagConstraints.CENTER);

        btn1.addActionListener(this);
        btn2.addActionListener(this);

        btn1.setFocusable(false);
        btn2.setFocusable(false);
    }

    public void actionPerformed(ActionEvent e) {
        CARD.removeAll();
        CARD.revalidate();
        CARD.repaint();

        if (e.getActionCommand().equals("PAY")) {
            CARD.add(new Window_4("payment"));
        } else if (e.getActionCommand().equals("BACK")) {
            CARD.add(new Window_2());
        }
    }
}

