package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static ui.DvmWindow.*;

// Window3-1
public class PaymentWindow extends DvmPanel {
    private JButton btn1;
    private JButton btn2;
    private JLabel totalPrice = new JLabel("<html>Total price:<br><center>" + ItemShowWindow.getTotalPrice() + "</center></html>", SwingConstants.CENTER);
    private static final int btnTotalPriceWidth = 100;
    private static final int btnTotalPriceHeight = 70;

    JPanel panel;

    public PaymentWindow(DvmPanel prevPanel) {
        super(prevPanel);
    }

    protected void init() {
        super.init();
        panel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();
        CARD.add(panel);
        panel.setBackground(Color.decode("#dcebf7"));

        addJLabel(panel);
        setJLabel(totalPrice, btnTotalPriceWidth, btnTotalPriceHeight, Color.decode("#cfd0d1"));

        btn1 = new JButton("PAY");
        btn2 = new JButton("BACK");
        addComponent(panel, btn1, 0, 55, 0, 0, 1, 1, 0.5, GridBagConstraints.CENTER);
        addComponent(panel, btn2, 10, 2, 2, 10, 4, 0, 0.5, GridBagConstraints.FIRST_LINE_END);
        addComponent(panel, totalPrice, 0, 50, 120, 0, 1, 1, 0.5, GridBagConstraints.CENTER);

        btn1.addActionListener(this);
        btn2.addActionListener(this);

        btn1.setFocusable(false);
        btn2.setFocusable(false);
    }

    public void actionPerformed(ActionEvent e) {
        resetCard();
        if (e.getActionCommand().equals("PAY")) {
            CARD.add(new ReadCardWindow(this, "payment"));
        } else if (e.getActionCommand().equals("BACK")) {
            prevPanel.init();
            CARD.add(prevPanel);
        }
    }
}

