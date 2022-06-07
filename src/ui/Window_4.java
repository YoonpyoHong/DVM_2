package ui;

import domain.payment.Verification;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
<<<<<<< Updated upstream
=======
import java.util.concurrent.atomic.AtomicInteger;
>>>>>>> Stashed changes

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import static domain.payment.Card.CARD_NUM_LENGTH;
import static ui.Window_1.selectedItemId;
import static ui.Window_2.dvmInfo;
import static ui.Window_2.selectedItemNum;

import static ui.DvmWindow.*;

public class Window_4 extends JPanel implements ActionListener {
    private String paymentType;
    private Verification verification;

    private static final JButton btn1 = new JButton("ENTER");
    private static final JButton btn2 = new JButton("BACK");

    private static final JTextField verCode = new JTextField(15);
    private static final JLabel time = new JLabel("<html>Time runout display<br><center>(60 sec)</center></html>", SwingConstants.CENTER);
    private static final JLabel notice = new JLabel("Please insert card's info", SwingConstants.CENTER);
    JPanel panel;
    Timer timer;

    public Window_4() {
        this(null, null);
        init();
    }
    public Window_4(String paymentType) {
        this(paymentType, null);
        init();
    }

    public Window_4(String paymentType, Verification verification) {
        super();
        System.out.println("Window4() with paymentType: " + paymentType + ", " + verification);
        this.paymentType = paymentType;
        this.verification = verification;
        init();
    }

    protected void init() {

        if(timer!=null){
            timer.stop();

            for (ActionListener listener : timer.getActionListeners()) {
                timer.removeActionListener(listener);
            }
        }
        AtomicInteger count = new AtomicInteger(60);

        verCode.setDocument(new JTextFieldLimit(CARD_NUM_LENGTH));
        panel = new JPanel(new GridBagLayout());
        c = new GridBagConstraints();
        card.add(panel);
        panel.setBackground(Color.decode("#dcebf7"));

        c.anchor = GridBagConstraints.FIRST_LINE_START;
        addJLabel(panel);

        setJLabel(time, 200, 50, Color.decode("#cfd0d1"));
        setJLabel(notice, 200, 50, Color.decode("#cfd0d1"));

        addComponent(panel,btn1, 0, 250, 0, 0, 0, 1, 0.5, GridBagConstraints.CENTER);
        addComponent(panel,btn2, 10, 0, 2, 10, 4, 0, 0.5, GridBagConstraints.FIRST_LINE_END);
        addComponent(panel,notice, 0, 130, 300, 0, 0, 1, 0.5, GridBagConstraints.CENTER);
        addComponent(panel,time, 0, 130, 150, 0, 0, 1, 0.5, GridBagConstraints.CENTER);
        addComponent(panel,verCode, 0, 50, 0, 0, 0, 1, 0.5, GridBagConstraints.CENTER);

        btn1.addActionListener(this);
        btn2.addActionListener(this);

        btn1.setFocusable(false);
        btn2.setFocusable(false);
<<<<<<< Updated upstream
        
        count = 60;
	    timer = new Timer(1000, new ActionListener() {
		      @Override
		      public void actionPerformed(ActionEvent e) {
			        count--;       	
		        	time.setText("Time runout (sec): " + Integer.toString(count));
		        	
		        	if(count==0){
		        		dispose();
		        		Window_1 returnHome = new Window_1(controller);
		        		((Timer) (e.getSource())).stop();
		        	}
		      	}
		    });
=======

	    timer = new Timer(1000, e -> {
              count.getAndDecrement();
              time.setText("Time run out (sec): " + count);

              if(count.get() ==0){
                  card.removeAll();
                  card.revalidate();
                  card.repaint();
                  card.add(new Window_1());
                  ((Timer) (e.getSource())).stop();
              }
            });
>>>>>>> Stashed changes
		timer.setInitialDelay(0);
		timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        card.removeAll();
        card.revalidate();
        card.repaint();

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
            System.out.println("itemId, itemNum = " + selectedItemId + ", " + selectedItemNum);
            if (paymentType.equals("payment")) {
                resMsg = controller.payment(selectedItemId, selectedItemNum, cardNum, 1234);
                card.add(new Window_1());
            } else if (paymentType.equals("prepayment")) {
                resMsg = controller.prepayment(selectedItemId, selectedItemNum, cardNum, 1234, dvmInfo[0]);
                card.add(new Window_5());
            } else if (paymentType.equals("cancelPrepayment")) {
                int price = controller.getItemManager().getItemList()[this.verification.getItemId() - 1].getItemPrice();
                int quantity = this.verification.getItemQuantity();
                controller.getPaymentManager().cancelPayment(controller.getCardReader(), price * quantity, cardNum);
                resMsg = "payment canceled";
                card.add(new Window_1());
            }
            if (resMsg.contains("error")) {
                /* TODO: some err dialog */
                System.err.println(resMsg);
                card.add(new Window_1());
            }
            if (this.paymentType.equals("cancelPrepayment")) {
                System.out.println("cancel payment: " + this.verification);
                controller.getVerificationManager().removeVerification(verification.getVerificationCode());
            }
        } else if (e.getActionCommand().equals("BACK")) {
            timer.stop();

            for (ActionListener listener : timer.getActionListeners()) {
                timer.removeActionListener(listener);
            }

            card.add(new Window_1());
        }
    }
}

