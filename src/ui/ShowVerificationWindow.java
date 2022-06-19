package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static ui.DvmWindow.*;

// Window5
public class ShowVerificationWindow extends DvmPanel {
    private final JLabel verificationCodeLabel = new JLabel("<html><center>Verification code:<br>1234567890</html>", SwingConstants.CENTER);

    private final JLabel locationLabel = new JLabel("Location: (x,y)", SwingConstants.CENTER);
    private final JLabel distanceLabel = new JLabel("Distance: m", SwingConstants.CENTER);

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

        setJLabel(verificationCodeLabel,120, 70, Color.decode("#cfd0d1"));
        addComponent(mainPanel, verificationCodeLabel, 0, 0, 120, 0, 1, 1, 0.5, GridBagConstraints.CENTER);

        setJLabel(locationLabel,100, 70, Color.decode("#cfd0d1"));
        addComponent(mainPanel, locationLabel, 0, 0, 300, 150, 1, 1, 0.5, GridBagConstraints.CENTER);

        setJLabel(distanceLabel,100, 70, Color.decode("#cfd0d1"));
        addComponent(mainPanel, distanceLabel, 0, 150, 300, 0, 1, 1, 0.5, GridBagConstraints.CENTER);

        JButton homeBtn = new JButton("HOME");
        homeBtn.setFocusable(false);
        homeBtn.addActionListener(this);
        addComponent(mainPanel, homeBtn, 0, 0, 0, 0, 1, 1, 0.5, GridBagConstraints.CENTER);

        JButton backBtn = new JButton("BACK");
        backBtn.setFocusable(false);
        backBtn.addActionListener(this);
        addComponent(mainPanel, backBtn, 10, 0, 2, 10, 4, 0, 0.5, GridBagConstraints.FIRST_LINE_END);
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
