package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static ui.DvmWindow.*;

// Window5
public class ShowVerificationWindow extends DvmPanel {
    private JButton btn1;
    private JButton btn2;

    private JLabel verCode = new JLabel("<html><center>Verification code:<br>1234567890</html>", SwingConstants.CENTER);

    private JLabel loc = new JLabel("Location: (x,y)", SwingConstants.CENTER);
    private JLabel distance = new JLabel("Distance: m", SwingConstants.CENTER);

    public ShowVerificationWindow() {
        this(null);
    }

    public ShowVerificationWindow(DvmPanel prevPanel) {
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

        setJLabel(verCode,120, 70, Color.decode("#cfd0d1"));
        addComponent(mainPanel,verCode, 0, 0, 120, 0, 1, 1, 0.5, GridBagConstraints.CENTER);

        setJLabel(loc,100, 70, Color.decode("#cfd0d1"));
        addComponent(mainPanel,loc, 0, 0, 300, 150, 1, 1, 0.5, GridBagConstraints.CENTER);

        setJLabel(distance,100, 70, Color.decode("#cfd0d1"));
        addComponent(mainPanel,distance, 0, 150, 300, 0, 1, 1, 0.5, GridBagConstraints.CENTER);

        btn1 = new JButton("HOME");
        btn1.setFocusable(false);
        btn1.addActionListener(this);
        addComponent(mainPanel,btn1, 0, 0, 0, 0, 1, 1, 0.5, GridBagConstraints.CENTER);

        btn2 = new JButton("BACK");
        btn2.setFocusable(false);
        btn2.addActionListener(this);
        addComponent(mainPanel,btn2, 10, 0, 2, 10, 4, 0, 0.5, GridBagConstraints.FIRST_LINE_END);
    }

    public void actionPerformed(ActionEvent e) {
        resetCard();
        if (e.getActionCommand().equals("HOME")) {
            CARD_PANEL.add(new HomeWindow());
        } else if (e.getActionCommand().equals("BACK")) {
            prevPanel.init();
            CARD_PANEL.add(prevPanel);
        }
    }
}
