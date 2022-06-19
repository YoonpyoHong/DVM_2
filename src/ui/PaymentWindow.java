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

    private JLabel totalPriceLabel;
    private JButton payBtn;
    private JButton backBtn;

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

        totalPriceLabel = new JLabel("<html>Total price:<br><center>" + ItemShowWindow.getTotalPrice() + "</center></html>", SwingConstants.CENTER);
        setJLabel(totalPriceLabel, TOTAL_PRICE_BUTTON_WIDTH, TOTAL_PRICE_BUTTON_HEIGHT, Color.decode("#cfd0d1"));
        addComponent(mainPanel, totalPriceLabel, 0, 50, 120, 0, 1, 1, 0.5, GridBagConstraints.CENTER);

        payBtn = new JButton("PAY");
        payBtn.setFocusable(false);
        payBtn.addActionListener(this);
        addComponent(mainPanel, payBtn, 0, 55, 0, 0, 1, 1, 0.5, GridBagConstraints.CENTER);

        backBtn = new JButton("BACK");
        backBtn.setFocusable(false);
        backBtn.addActionListener(this);
        addComponent(mainPanel, backBtn, 10, 2, 2, 10, 4, 0, 0.5, GridBagConstraints.FIRST_LINE_END);
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

