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
import javax.swing.SwingConstants;

import static domain.payment.Card.CARD_NUM_LENGTH;
import static ui.Window_1.selectedItemId;
import static ui.Window_2.dvmInfo;
import static ui.Window_2.selectedItemNum;

public class Window_4 extends DvmWindow {
    private String paymentType;
    private Verification verification;

    private static final JButton btn1 = new JButton("ENTER");
    private static final JButton btn2 = new JButton("BACK");

    private static final JTextField verCode = new JTextField(15);
    private static final JLabel time = new JLabel("<html>Time runout display<br><center>(60 sec)</center></html>", SwingConstants.CENTER);
    private static final JLabel notice = new JLabel("Please insert card's info", SwingConstants.CENTER);

    public Window_4(Controller controller) {
        super(controller);
    }

    public Window_4(Controller controller, String paymentType) {
        this(controller, paymentType, null);
    }

    public Window_4(Controller controller, String paymentType, Verification verification) {
        super(controller);
        System.out.println("Window4() with paymentType: " + paymentType + ", " + verification);
        this.paymentType = paymentType;
        this.verification = verification;
    }

    protected void init() {
        verCode.setDocument(new JTextFieldLimit(CARD_NUM_LENGTH));
        panel = new JPanel(new GridBagLayout());
        c = new GridBagConstraints();
        frame.add(panel);
        vmID.setBackground(Color.decode("#cfd0d1"));
        panel.setBackground(Color.decode("#dcebf7"));

        //padding for top, left, bottom, right
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        addJLable(vmID, 10, 10, 2, 2, true);

        setJLable(time, 200, 50, true, Color.decode("#cfd0d1"), 1);
        setJLable(notice, 200, 50, true, Color.decode("#cfd0d1"), 1);

        addComponent(btn1, 0, 250, 0, 0, 0, 1, 0.5, GridBagConstraints.CENTER);
        addComponent(btn2, 10, 0, 2, 10, 4, 0, 0.5, GridBagConstraints.FIRST_LINE_END);
        addComponent(notice, 0, 130, 300, 0, 0, 1, 0.5, GridBagConstraints.CENTER);
        addComponent(time, 0, 130, 150, 0, 0, 1, 0.5, GridBagConstraints.CENTER);
        addComponent(verCode, 0, 50, 0, 0, 0, 1, 0.5, GridBagConstraints.CENTER);

        btn1.addActionListener(this);
        btn2.addActionListener(this);

        btn1.setFocusable(false);
        btn2.setFocusable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ENTER")) {
            /* TODO: also input card password */
//			show JDialog (successful transaction, drink in dispensed)
            String inputCardNum = verCode.getText();
            String cardNum = controller.getCardReader().encodeCardNum(inputCardNum);
            String resMsg = "";
            System.out.println("itemId, itemNum = " + selectedItemId + ", " + selectedItemNum);
            if (paymentType.equals("payment")) {
                resMsg = controller.payment(selectedItemId, selectedItemNum, cardNum, 1234);
            } else if (paymentType.equals("prepayment")) {
                resMsg = controller.prepayment(selectedItemId, selectedItemNum, cardNum, 1234, dvmInfo[0]);
            } else if (paymentType.equals("cancelPrepayment")) {
                int price = controller.getItemManager().getItemList()[this.verification.getItemId() - 1].getItemPrice();
                int quantity = this.verification.getItemQuantity();
                controller.getPaymentManager().cancelPayment(controller.getCardReader(), price * quantity, cardNum);
                resMsg = "payment canceled";
            }
            if (resMsg.contains("error")) {
                /* TODO: some err dialog */
                System.err.println(resMsg);
            }
            if (this.paymentType.equals("cancelPrepayment")) {
                System.out.println("cancel payment: " + this.verification);
                controller.getVerificationManager().removeVerification(verification.getVerificationCode());
            }
            this.dispose();
            new Window_1(controller);
        } else if (e.getActionCommand().equals("BACK")) {
            this.dispose();
            new Window_3_1(controller);
        }
    }
}

