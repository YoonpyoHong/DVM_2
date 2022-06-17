package ui;

import domain.payment.Order;
import domain.payment.Verification;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicInteger;

import static domain.payment.Card.CARD_NUM_LENGTH;
import static ui.DvmWindow.*;
import static ui.ItemShowWindow.dvmInfo;

// Window4
public class ProcessPaymentWindow extends DvmPanel {
    private String paymentType;
    private Verification verification;

    private JButton btn1;
    private JButton btn2;

    private JTextField verCode = new JTextField(15);
    private JLabel time;

    private Timer timer;

    private Order order;

    private ProcessPaymentWindow(DvmPanel prevPanel, String paymentType, Verification verification, Order order) {
        super(prevPanel);
        System.out.println("Window4() with paymentType: " + paymentType + ", " + verification);
        this.paymentType = paymentType;
        this.verification = verification;
        this.order = order;
        init();
    }

    public static class Builder {
        private DvmPanel prevPanel;
        private String paymentType;
        private Verification verification;
        private Order order;

        public Builder() {
            this.prevPanel = null;
            this.paymentType = null;
            this.verification = null;
            this.order = null;
        }

        public Builder setPrevPanel(DvmPanel prevPanel) {
            this.prevPanel = prevPanel;
            return this;
        }

        public Builder setpaymentType(String paymentType) {
            this.paymentType = paymentType;
            return this;
        }

        public Builder setVerification(Verification verification) {
            this.verification = verification;
            return this;
        }

        public Builder setOrder(Order order) {
            this.order = order;
            return this;
        }

        public ProcessPaymentWindow build() {
            return new ProcessPaymentWindow(prevPanel, paymentType, verification, order);
        }
    }

    @Override
    void init() {
        initLayout();
        initTimer();
    }

    @Override
    protected void initLayout() {
        super.initLayout();

        CARD_PANEL.add(mainPanel);

        mainPanel.setBackground(Color.decode("#dcebf7"));
        mainPanel = new JPanel(new GridBagLayout());

        constraints = new GridBagConstraints();

        verCode.setDocument(new JTextFieldLimit(CARD_NUM_LENGTH));
        addComponent(mainPanel,verCode, 0, 50, 0, 0, 0, 1, 0.5, GridBagConstraints.CENTER);

        time = new JLabel("<html>Time runout display<br><center>(60 sec)</center></html>", SwingConstants.CENTER);
        setJLabel(time, 200, 50, Color.decode("#cfd0d1"));
        addComponent(mainPanel,time, 0, 130, 150, 0, 0, 1, 0.5, GridBagConstraints.CENTER);

        JLabel notice = new JLabel("Please insert card's info", SwingConstants.CENTER);
        setJLabel(notice, 200, 50, Color.decode("#cfd0d1"));
        addComponent(mainPanel,notice, 0, 130, 300, 0, 0, 1, 0.5, GridBagConstraints.CENTER);

        btn1 = new JButton("ENTER");
        btn1.setFocusable(false);
        btn1.addActionListener(this);
        addComponent(mainPanel,btn1, 0, 250, 0, 0, 0, 1, 0.5, GridBagConstraints.CENTER);

        btn2 = new JButton("BACK");
        btn2.setFocusable(false);
        btn2.addActionListener(this);
        addComponent(mainPanel,btn2, 10, 0, 2, 10, 4, 0, 0.5, GridBagConstraints.FIRST_LINE_END);
    }

    private void initTimer() {
        AtomicInteger count = new AtomicInteger(60);
        if (timer != null) {
            timer.stop();
            for (ActionListener listener : timer.getActionListeners()) {
                timer.removeActionListener(listener);
            }
        }
        timer = new Timer(1000, e -> {
            count.getAndDecrement();
            time.setText("Time run out (sec): " + count);
            if (count.get() == 0) {
                resetCard();
                CARD_PANEL.add(new HomeWindow());
                ((Timer) (e.getSource())).stop();
            }
        });
        timer.setInitialDelay(0);
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        resetCard();
        if (e.getActionCommand().equals("ENTER")) {
            timer.stop();
            for (ActionListener listener : timer.getActionListeners()) {
                timer.removeActionListener(listener);
            }
            /* TODO: also input card password */
//			show JDialog (successful transaction, drink in dispensed)
            String inputCardNum = verCode.getText();
            String cardNum = controller.getCardReader().encodeCardNum(inputCardNum);
            String resMsg = "";
            if (paymentType.equals("cancelPrepayment")) {
                int price = controller.getItemManager().getItemList()[this.verification.getItemId() - 1].getItemPrice();
                int quantity = this.verification.getItemQuantity();
                controller.getPaymentManager().cancelPayment(controller.getCardReader(), price * quantity, cardNum);

                resMsg = "prepayment canceled";
                new DvmDialog(resMsg);
                CARD_PANEL.add(new HomeWindow());

                System.out.println("cancel payment: " + this.verification);
                controller.getVerificationManager().removeVerification(verification.getVerificationCode());
                return;
            }
            int selectedItemNum = order.getQuantity();
            int selectedItemId = order.getItem().getItemId();
            System.out.println("itemId, itemNum = " + selectedItemId + ", " + selectedItemNum);
            if (paymentType.equals("payment")) {
                resMsg = controller.payment(selectedItemId, selectedItemNum, cardNum, 1234);
                CARD_PANEL.add(new HomeWindow());
            } else if (paymentType.equals("prepayment")) {
                resMsg = controller.prepayment(selectedItemId, selectedItemNum, cardNum, 1234, dvmInfo[0]);
                CARD_PANEL.add(new ShowVerificationWindow());
            }
            if (resMsg.contains("error")) {
                /* TODO: some err dialog */
                System.err.println(resMsg);
                CARD_PANEL.add(new HomeWindow());
            }
        } else if (e.getActionCommand().equals("BACK")) {
            timer.stop();
            for (ActionListener listener : timer.getActionListeners()) {
                timer.removeActionListener(listener);
            }
            CARD_PANEL.add(new HomeWindow());
        }
    }

    @Override
    protected void addVmIdLabel() {
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        super.addVmIdLabel();
    }
}
