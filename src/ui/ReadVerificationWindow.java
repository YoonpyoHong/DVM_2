package ui;

import domain.payment.Verification;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static domain.payment.VerificationManager.CODE_LENGTH;
import static ui.DvmWindow.*;

// Window6
public class ReadVerificationWindow extends DvmPanel {
    private final JTextField verificationCodeTextField = new JTextField(15);
    private final JLabel noticeLabel = new JLabel("Please insert verification code:");

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

        verificationCodeTextField.setDocument(new JTextFieldLimit(CODE_LENGTH));
        addComponent(mainPanel, verificationCodeTextField, 0, 20, 130, 20, 1, 1, 0.5, GridBagConstraints.CENTER);

        noticeLabel.setBackground(Color.decode("#cfd0d1"));

        JButton enterBtn = new JButton("ENTER");
        enterBtn.setFocusable(false);
        enterBtn.addActionListener(this);
        addComponent(mainPanel, enterBtn, 0, 0, 50, 5, 1, 1, 0.5, GridBagConstraints.CENTER);

        JButton backBtn = new JButton("BACK");
        backBtn.setFocusable(false);
        backBtn.addActionListener(this);
        addComponent(mainPanel, backBtn, 10, 2, 2, 10, 4, 0, 0.5, GridBagConstraints.FIRST_LINE_END);

        noticeLabel.setOpaque(true);
        addComponent(mainPanel, noticeLabel, 0, 0, 200, 0, 1, 1, 0.5, GridBagConstraints.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ENTER")) {
            String inputAuthCode = verificationCodeTextField.getText();
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
                CARD_PANEL.add(new ReadCardWindow.Builder()
                        .setPrevPanel(prevPanel)
                        .setpaymentType("cancelPrepayment")
                        .setVerification(verification)
                        .build()
                );
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

