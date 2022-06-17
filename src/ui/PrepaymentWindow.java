package ui;

import domain.payment.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static ui.DvmWindow.*;

// Window3-2
public class PrepaymentWindow extends DvmPanel {
    private JButton btn1;
    private JButton btn2;

    private Order order;

    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 70;

    public PrepaymentWindow(DvmPanel prevPanel, Order order) {
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
        setJLabel(totalPrice, BUTTON_WIDTH, BUTTON_HEIGHT, Color.decode("#cfd0d1"));
        addComponent(mainPanel,totalPrice, 0, 0, 120, 0, 1, 1, 0.5, GridBagConstraints.CENTER);

        JLabel loc = new JLabel(String.format("Location: (%d, %d)", ItemShowWindow.dvmInfo[1], ItemShowWindow.dvmInfo[2]), SwingConstants.CENTER);
        setJLabel(loc, BUTTON_WIDTH, BUTTON_HEIGHT, Color.decode("#cfd0d1"));
        addComponent(mainPanel,loc, 0, 0, 300, 150, 1, 1, 0.5, GridBagConstraints.CENTER);

        JLabel distance = new JLabel(String.format("Distance: %.1f", Math.sqrt(ItemShowWindow.dvmInfo[3])), SwingConstants.CENTER);
        setJLabel(distance, BUTTON_WIDTH, BUTTON_HEIGHT, Color.decode("#cfd0d1"));
        addComponent(mainPanel,distance, 0, 150, 300, 0, 1, 1, 0.5, GridBagConstraints.CENTER);

        btn1 = new JButton("PAY");
        btn1.setFocusable(false);
        btn1.addActionListener(this);
        addComponent(mainPanel,btn1, 0, 0, 0, 0, 1, 1, 0.5, GridBagConstraints.CENTER);

        btn2 = new JButton("BACK");
        btn2.setFocusable(false);
        btn2.addActionListener(this);
        addComponent(mainPanel,btn2, 10, 0, 2, 10, 4, 0, 0.5, GridBagConstraints.FIRST_LINE_END);
    }

    public void actionPerformed(ActionEvent e) {
        resetCard();
        if (e.getActionCommand().equals("PAY")) {
            CARD_PANEL.add(new ProcessPaymentWindow.Builder().setPrevPanel(prevPanel).setpaymentType("prepayment").setOrder(order).build());
        } else if (e.getActionCommand().equals("BACK")) {
            prevPanel.init();
            CARD_PANEL.add(prevPanel);
        }
    }

}

