package ui;

import domain.app.Controller;
import domain.payment.Verification;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import static domain.payment.VerificationManager.CODE_LENGTH;

public class Window_6 extends DvmWindow {
    private static final JButton btn1 = new JButton("ENTER");
    private static final JButton btn2 = new JButton("BACK");
    private static final JTextField verCode = new JTextField(15);
    private static final JLabel notice = new JLabel("Please insert verification code:");

    public Window_6(Controller controller) {
        super(controller);
    }

    protected void init() {
        verCode.setDocument(new JTextFieldLimit(CODE_LENGTH));
        panel = new JPanel(new GridBagLayout());
        c = new GridBagConstraints();
        frame.add(panel);

        vmID.setBackground(Color.decode("#cfd0d1"));
        notice.setBackground(Color.decode("#cfd0d1"));
        panel.setBackground(Color.decode("#dcebf7"));

        addJLable(vmID, 2, 10, 2, 2, true);

        addComponent(btn1, 0, 0, 50, 5, 1, 1, 0.5, GridBagConstraints.CENTER);
        addComponent(btn2, 10, 2, 2, 10, 4, 0, 0.5, GridBagConstraints.FIRST_LINE_END);
        notice.setOpaque(true);
        addComponent(notice, 0, 0, 200, 0, 1, 1, 0.5, GridBagConstraints.CENTER);
        addComponent(verCode, 0, 20, 130, 20, 1, 1, 0.5, GridBagConstraints.CENTER);

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
                this.dispose();
                new Window_1(controller);
            } else if (verification.getVerificationValidity()) {
                /* TODO: some normal get drink dialog */
                resMsg = "prepayment success";
                System.out.println(resMsg);
                this.dispose();
                new Window_1(controller);
            } else {
                /* TODO: some cancel prepayment dialog */
                System.out.println("before cancel prepayment, " + verification);
                resMsg = "error: cancel prepayment";
                this.dispose();
                new Window_4(controller, "cancelPrepayment", verification);
            }
            //show JDialog
            //dispose JDialog and this window after 15 second
            //not implemented yet
        } else if (e.getActionCommand().equals("BACK")) {
            this.dispose();
            new Window_1(controller);
        }
    }
}

