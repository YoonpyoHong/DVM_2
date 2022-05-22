package ui;

import domain.app.Controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class Window_4 extends DvmWindow {
    private static final JButton btn1 = new JButton("ENTER");
    private static final JButton btn2 = new JButton("BACK");
    private static final GridBagConstraints c = new GridBagConstraints();

    private static final JTextField verCode = new JTextField(11);
    private static final JLabel time = new JLabel("<html>Time runout display<br><center>(60 sec)</center></html>", SwingConstants.CENTER);
    private static final JLabel notice = new JLabel("Please insert card's info", SwingConstants.CENTER);


    public Window_4(Controller controller) {
        super(controller);
    }

    protected void init() {
        panel = new JPanel(new GridBagLayout());
        frame.add(panel);
        vmID.setBackground(Color.decode("#cfd0d1"));
        panel.setBackground(Color.decode("#dcebf7"));

        //padding for top, left, bottom, right
        c.insets = new Insets(10, 10, 2, 2);
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        vmID.setOpaque(true);
        panel.add(vmID, c);

        time.setPreferredSize(new Dimension(200, 50));
        time.setOpaque(true);
        time.setBorder(BorderFactory.createLineBorder(Color.decode("#cfd0d1"), 1));

        notice.setPreferredSize(new Dimension(200, 50));
        notice.setOpaque(true);
        notice.setBorder(BorderFactory.createLineBorder(Color.decode("#cfd0d1"), 1));

        c.insets = new Insets(10, 0, 2, 10);
        c.anchor = GridBagConstraints.FIRST_LINE_END; //top corner right
        c.weightx = 0.5;
        c.gridx = 4;
        c.gridy = 0;
        panel.add(btn2, c);

        c.insets = new Insets(0, 130, 300, 0);
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER; //center
        c.weighty = 0.5;
        panel.add(notice, c);

        c.insets = new Insets(0, 130, 150, 0);
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER; //center
        c.weighty = 0.5;
        panel.add(time, c);

        c.insets = new Insets(0, 250, 0, 0);
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER; //center
        c.weighty = 0.5;
        panel.add(btn1, c);

        c.insets = new Insets(0, 50, 0, 0);
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER; //center
        c.weighty = 0.5;

        verCode.setDocument(new JTextFieldLimit(10));
        panel.add(verCode, c);

        btn1.addActionListener(this);
        btn2.addActionListener(this);

        btn1.setFocusable(false);
        btn2.setFocusable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "ENTER") {
//			show JDialog (successful transaction, drink in dispensed)
            dispose();
            Window_1 nextWindow = new Window_1(controller);

        } else if (e.getActionCommand() == "BACK") {
            dispose();
            Window_3_1 backWindow = new Window_3_1(controller);
        }
    }

}

