package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.DvmWindow.*;


public class Window_7 extends DvmPanel {
    private JButton btn1;
    private JButton btn2;
    private static final JTextField pwd = new JTextField(15);

    private static final JLabel notice = new JLabel("<html><center>LOGIN PAGE<br>Please enter password<br>in the textfield below:</html>");
    private static final EmptyBorder eb = new EmptyBorder(new Insets(10, 10, 10, 10));

    protected void init() {
        super.init();
        notice.setPreferredSize(new Dimension(150, 70));
        notice.setBorder(eb);
        pwd.setDocument(new JTextFieldLimit(10));

        JPanel panel = new JPanel(new GridBagLayout());
        c = new GridBagConstraints();
        CARD.add(panel);
        VM_ID.setBackground(Color.decode("#cfd0d1"));
        notice.setBackground(Color.decode("#cfd0d1"));
        panel.setBackground(Color.decode("#dcebf7"));

        addJLabel(panel);

        btn1 = new JButton("LOGIN");
        btn2 = new JButton("BACK");
        addComponent(panel,btn1, 0, 0, 50, 5, 1, 1, 0.5, GridBagConstraints.CENTER);
        addComponent(panel,btn2, 10, 2, 2, 10, 4, 0, 0.5, GridBagConstraints.FIRST_LINE_END);
        notice.setOpaque(true);
        addComponent(panel,notice, 0, 0, 260, 0, 1, 1, 0.5, GridBagConstraints.CENTER);
        addComponent(panel,pwd, 0, 20, 130, 20, 1, 1, 0.5, GridBagConstraints.CENTER);

        btn1.addActionListener(this);
        btn2.addActionListener(this);

        btn1.setFocusable(false);
        btn2.setFocusable(false);
    }

    public void actionPerformed(ActionEvent e) {
        resetCard();
        if (e.getActionCommand().equals("LOGIN")) {
            boolean authorized = controller.getAccountManager().verifyLoginInfo(pwd.getText());
            if (authorized) {
            } else {
                /* TODO: put smth error message dialog */
                System.out.println("Wrong PassWord");
            }
        } else if (e.getActionCommand().equals("BACK")) {
        }
    }
}

