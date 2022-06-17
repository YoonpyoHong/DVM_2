package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

import static ui.DvmWindow.*;

// Window7
public class AdminLoginWindow extends DvmPanel {
    private JButton btn1;
    private JButton btn2;
    private JTextField pwd = new JTextField(15);

    private JLabel notice = new JLabel("<html><center>LOGIN PAGE<br>Please enter password<br>in the textfield below:</html>");
    private EmptyBorder eb = new EmptyBorder(new Insets(10, 10, 10, 10));

    public AdminLoginWindow() {
        this(null);
    }

    public AdminLoginWindow(DvmPanel prevPanel) {
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

        notice.setBackground(Color.decode("#cfd0d1"));
        notice.setPreferredSize(new Dimension(150, 70));
        notice.setBorder(eb);
        notice.setOpaque(true);
        addComponent(mainPanel,notice, 0, 0, 260, 0, 1, 1, 0.5, GridBagConstraints.CENTER);

        pwd.setDocument(new JTextFieldLimit(10));
        addComponent(mainPanel,pwd, 0, 20, 130, 20, 1, 1, 0.5, GridBagConstraints.CENTER);

        btn1 = new JButton("LOGIN");
        btn1.setFocusable(false);
        btn1.addActionListener(this);
        addComponent(mainPanel,btn1, 0, 0, 50, 5, 1, 1, 0.5, GridBagConstraints.CENTER);

        btn2 = new JButton("BACK");
        btn2.setFocusable(false);
        btn2.addActionListener(this);
        addComponent(mainPanel,btn2, 10, 2, 2, 10, 4, 0, 0.5, GridBagConstraints.FIRST_LINE_END);
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

