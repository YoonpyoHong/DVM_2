package ui;

import domain.payment.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static ui.DvmWindow.*;

// Window3-1
public class PaymentWindow extends DvmPanel {
    private static final int TOTAL_PRICE_BUTTON_WIDTH = 100;
    private static final int TOTAL_PRICE_BUTTON_HEIGHT = 70;

    private JButton btn1;
    private JButton btn2;
    private Order order;


    public PaymentWindow(DvmPanel prevPanel, Order order) {
        super(prevPanel);
        this.order = order;
        init();
    }

    @Override
    void init() {
        initLayout();
    }

    @Override
    protected void initLayout() {
        super.initLayout();

        JLabel totalPrice = new JLabel("<html>Total price:<br><center>" + ItemShowWindow.getTotalPrice() + "</center></html>", SwingConstants.CENTER);
        setJLabel(totalPrice, TOTAL_PRICE_BUTTON_WIDTH, TOTAL_PRICE_BUTTON_HEIGHT, Color.decode("#cfd0d1"));
        addComponent(mainPanel, totalPrice, 0, 50, 120, 0, 1, 1, 0.5, GridBagConstraints.CENTER);

        btn1 = new JButton("PAY");
        btn1.setFocusable(false);
        btn1.addActionListener(this);
        addComponent(mainPanel, btn1, 0, 55, 0, 0, 1, 1, 0.5, GridBagConstraints.CENTER);

        btn2 = new JButton("BACK");
        btn2.setFocusable(false);
        btn2.addActionListener(this);
        addComponent(mainPanel, btn2, 10, 2, 2, 10, 4, 0, 0.5, GridBagConstraints.FIRST_LINE_END);
    }

    public void actionPerformed(ActionEvent e) {
        resetCard();
        if (e.getActionCommand().equals("PAY")) {
            CARD_PANEL.add(new ReadCardWindow.Builder()
                    .setPrevPanel(prevPanel)
                    .setpaymentType("payment")
                    .setOrder(order)
                    .build()
            );
        } else if (e.getActionCommand().equals("BACK")) {
            prevPanel.init();
            CARD_PANEL.add(prevPanel);
        }
    }
}

