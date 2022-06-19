package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

import static ui.DvmWindow.*;

// Window7
public class AdminLoginWindow extends DvmPanel {
    private final JTextField pwdTextField = new JTextField(15);

    private final JLabel noticeLabel = new JLabel("<html><center>LOGIN PAGE<br>Please enter password</html>");
    private final EmptyBorder emptyBorder = new EmptyBorder(new Insets(10, 10, 10, 10));

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

        noticeLabel.setBackground(Color.decode("#cfd0d1"));
        noticeLabel.setPreferredSize(new Dimension(150, 70));
        noticeLabel.setBorder(emptyBorder);
        noticeLabel.setOpaque(true);
        addComponent(mainPanel, noticeLabel, 0, 0, 260, 0, 1, 1, 0.5, GridBagConstraints.CENTER);

        pwdTextField.setDocument(new JTextFieldLimit(10));
        addComponent(mainPanel, pwdTextField, 0, 20, 130, 20, 1, 1, 0.5, GridBagConstraints.CENTER);

        JButton loginBtn = new JButton("LOGIN");
        loginBtn.setFocusable(false);
        loginBtn.addActionListener(this);
        addComponent(mainPanel, loginBtn, 0, 0, 50, 5, 1, 1, 0.5, GridBagConstraints.CENTER);

        JButton backBtn = new JButton("BACK");
        backBtn.setFocusable(false);
        backBtn.addActionListener(this);
        addComponent(mainPanel, backBtn, 10, 2, 2, 10, 4, 0, 0.5, GridBagConstraints.FIRST_LINE_END);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("LOGIN")) {
            boolean authorized = controller.getAccountManager().verifyLoginInfo(pwdTextField.getText());
            if (authorized) {
                resetCard();
                CARD_PANEL.add(new AdminWindow(this));
            } else {
                String resMsg = "Wrong PassWord";
                new DvmDialog(resMsg);
            }
        } else if (e.getActionCommand().equals("BACK")) {
            resetCard();
            prevPanel.init();
            CARD_PANEL.add(prevPanel);
        }
    }
}

