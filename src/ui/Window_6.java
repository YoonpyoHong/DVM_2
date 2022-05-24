package ui;

import domain.app.Controller;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import static domain.payment.VerificationManager.CODE_LEN;

public class Window_6 extends DvmWindow {
    private static final JButton btn1 = new JButton("ENTER");
    private static final JButton btn2 = new JButton("BACK");
    private static final JTextField verCode = new JTextField(15);
    private static final JLabel notice = new JLabel("Please insert verification code:");

    public Window_6(Controller controller) {
        super(controller);
    }

    protected void init() {
        verCode.setDocument(new JTextFieldLimit(CODE_LEN));
        panel = new JPanel(new GridBagLayout());
        c = new GridBagConstraints();
        frame.add(panel);

        vmID.setBackground(Color.decode("#cfd0d1"));
        notice.setBackground(Color.decode("#cfd0d1"));
        panel.setBackground(Color.decode("#dcebf7"));

        addJLable(2, 10, 2, 2, true, vmID);

        addComponent(0, 0, 50, 5, 1, 1, 0.5, GridBagConstraints.CENTER, btn1);
        addComponent(10, 2, 2, 10, 4, 0, 0.5, GridBagConstraints.FIRST_LINE_END, btn2);
        notice.setOpaque(true);
        addComponent(0, 0, 200, 0, 1, 1, 0.5, GridBagConstraints.CENTER, notice);
        addComponent(0, 20, 130, 20, 1, 1, 0.5, GridBagConstraints.CENTER, verCode);

        btn1.addActionListener(this);
        btn2.addActionListener(this);

        btn1.setFocusable(false);
        btn2.setFocusable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ENTER")) {
            String inputCode = verCode.getText();
            System.err.println("inputCode = " + inputCode);
            String resMsg = controller.comfirmVerification(inputCode);
            System.err.println(resMsg);
            if (resMsg.equals("valid prepayment")) {
                /* TODO: some normal get drink dialog */
            } else if (resMsg.equals("invalid prepayment")) {
                /* TODO: some cancle payment dialog */
            } else {
                /* TODO: some error dialog */
            }
            //show JDialog
            //dispose JDialog and this window after 15 second
            //not implemented yet
            this.dispose();
            new Window_1(controller);
        } else if (e.getActionCommand().equals("BACK")) {
            this.dispose();
            new Window_1(controller);
        }
    }
}

