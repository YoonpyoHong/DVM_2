package ui;

import domain.payment.Verification;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static domain.payment.VerificationManager.CODE_LENGTH;
import static ui.DvmWindow.*;

// Window6
public class ReadVerificationWindow extends DvmPanel {
    private JButton btn1;
    private JButton btn2;
    private JTextField verCode = new JTextField(15);
    private JLabel notice = new JLabel("Please insert verification code:");

    public ReadVerificationWindow(DvmPanel prevPanel) {
        super(prevPanel);
        init();
    }

    @Override
    void init() {
        initLayout();
    }

    @Override
    protected void initLayout() {
        super.initLayout();

        verCode.setDocument(new JTextFieldLimit(CODE_LENGTH));
        addComponent(mainPanel,verCode, 0, 20, 130, 20, 1, 1, 0.5, GridBagConstraints.CENTER);

        notice.setBackground(Color.decode("#cfd0d1"));

        btn1 = new JButton("ENTER");
        btn1.setFocusable(false);
        btn1.addActionListener(this);
        addComponent(mainPanel,btn1, 0, 0, 50, 5, 1, 1, 0.5, GridBagConstraints.CENTER);

        btn2 = new JButton("BACK");
        btn2.setFocusable(false);
        btn2.addActionListener(this);
        addComponent(mainPanel,btn2, 10, 2, 2, 10, 4, 0, 0.5, GridBagConstraints.FIRST_LINE_END);

        notice.setOpaque(true);
        addComponent(mainPanel,notice, 0, 0, 200, 0, 1, 1, 0.5, GridBagConstraints.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ENTER")) {
            String inputAuthCode = verCode.getText();
            System.out.println("inputAuthCode = " + inputAuthCode);
            Verification verification = controller.comfirmVerification(inputAuthCode);
            String resMsg;
            if (verification == null) {
                /* TODO: some error dialog */
                resMsg = "error: invalid verificationCode";
                System.out.println(resMsg);
                new DvmDialog(resMsg);
            } else if (verification.getVerificationValidity()) {
                /* TODO: some normal get drink dialog */
                controller.getVerificationManager().removeVerification(inputAuthCode);
                resMsg = "prepayment success";
                System.out.println(resMsg);
                new DvmDialog(resMsg);
                resetCard();
                prevPanel.init();
                CARD_PANEL.add(prevPanel);
            } else {
                /* TODO: some cancel prepayment dialog */
                System.out.println("before cancel prepayment, " + verification);
                resMsg = "error: process to cancel prepayment";
                new DvmDialog(resMsg);
                resetCard();
                CARD_PANEL.add(new ProcessPaymentWindow.Builder().setPrevPanel(prevPanel).setpaymentType("cancelPrepayment").setVerification(verification).build());
            }
            //show JDialog
            //dispose JDialog and this window after 15 second
            //not implemented yet
        } else if (e.getActionCommand().equals("BACK")) {
            resetCard();
            prevPanel.init();
            CARD_PANEL.add(prevPanel);
        }
    }
}

