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

public class Window_6 extends DvmWindow {
    private static final JButton btn1 = new JButton("ENTER");
    private static final JButton btn2 = new JButton("BACK");
    private static final JTextField verCode = new JTextField(15);

    private static final JLabel notice = new JLabel("Please insert verification code:");

    public Window_6(Controller controller) {
        super(controller);
    }

    protected void init() {
        //set limit 10 digits for verification code:
        verCode.setDocument(new JTextFieldLimit(10));

        panel = new JPanel(new GridBagLayout());
        c = new GridBagConstraints();
        frame.add(panel);
        vmID.setBackground(Color.decode("#cfd0d1"));
        notice.setBackground(Color.decode("#cfd0d1"));
        panel.setBackground(Color.decode("#dcebf7"));

        //padding for top, left, bottom, right
        c.insets = new Insets(2, 10, 2, 2);
        vmID.setOpaque(true);
        panel.add(vmID, c);

        c.insets = new Insets(10, 2, 2, 10);
        c.anchor = GridBagConstraints.FIRST_LINE_END; //top corner right
        c.weightx = 0.5;
        c.gridx = 4;
        c.gridy = 0;
        panel.add(btn2, c);

        c.insets = new Insets(0, 0, 200, 0);
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER; //center
        c.weighty = 0.5;
        notice.setOpaque(true);
        panel.add(notice, c);

        c.insets = new Insets(0, 20, 130, 20);
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER; //center
        c.weighty = 0.5;
        panel.add(verCode, c);

        c.insets = new Insets(0, 0, 50, 5);
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER; //center
        c.weighty = 0.5;
        panel.add(btn1, c);

        btn1.addActionListener(this);
        btn2.addActionListener(this);

        btn1.setFocusable(false);
        btn2.setFocusable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ENTER")) {
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

