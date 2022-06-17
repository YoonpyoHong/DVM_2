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
    private static final JTextField verCode = new JTextField(15);
    private static final JLabel notice = new JLabel("Please insert verification code:");
    JPanel panel;

    public ReadVerificationWindow(DvmPanel prevPanel) {
        super(prevPanel);
    }

    protected void init() {
        super.init();
        verCode.setDocument(new JTextFieldLimit(CODE_LENGTH));
        panel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();
        CARD.add(panel);

        notice.setBackground(Color.decode("#cfd0d1"));
        panel.setBackground(Color.decode("#dcebf7"));

        addJLabel(panel);

        btn1 = new JButton("ENTER");
        btn2 = new JButton("BACK");
        addComponent(panel,btn1, 0, 0, 50, 5, 1, 1, 0.5, GridBagConstraints.CENTER);
        addComponent(panel,btn2, 10, 2, 2, 10, 4, 0, 0.5, GridBagConstraints.FIRST_LINE_END);
        notice.setOpaque(true);
        addComponent(panel,notice, 0, 0, 200, 0, 1, 1, 0.5, GridBagConstraints.CENTER);
        addComponent(panel,verCode, 0, 20, 130, 20, 1, 1, 0.5, GridBagConstraints.CENTER);

        btn1.addActionListener(this);
        btn2.addActionListener(this);

        btn1.setFocusable(false);
        btn2.setFocusable(false);
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
                CARD.add(prevPanel);
            } else {
                /* TODO: some cancel prepayment dialog */
                System.out.println("before cancel prepayment, " + verification);
                resMsg = "error: process to cancel prepayment";
                new DvmDialog(resMsg);
                resetCard();
                CARD.add(new ReadCardWindow(this, "cancelPrepayment", verification));
            }
            //show JDialog
            //dispose JDialog and this window after 15 second
            //not implemented yet
        } else if (e.getActionCommand().equals("BACK")) {
            resetCard();
            prevPanel.init();
            CARD.add(prevPanel);
        }
    }
}

