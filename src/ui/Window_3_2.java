package ui;

import domain.app.Controller;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Window_3_2 extends DvmWindow {
    private static final JButton btn1 = new JButton("PAY");
    private static final JButton btn2 = new JButton("BACK");

    private static final JLabel totalPrice = new JLabel("<html>Total price:<br><center>" + Window_2.getTotalPrice() + "</center></html>", SwingConstants.CENTER);
    private static final JLabel loc = new JLabel(String.format("Location: (%d, %d)", Window_2.dvmInfo[1], Window_2.dvmInfo[2]), SwingConstants.CENTER);
    private static final JLabel distance = new JLabel(String.format("Distance: %.1f", Math.sqrt(Window_2.dvmInfo[3])), SwingConstants.CENTER);

    private static final int btnWidth = 100;
    private static final int btnHeight = 70;

    public Window_3_2(Controller controller) {
        super(controller);
    }

    protected void init() {
        panel = new JPanel(new GridBagLayout());
        c = new GridBagConstraints();
        frame.add(panel);
        vmID.setBackground(Color.decode("#cfd0d1"));
        panel.setBackground(Color.decode("#dcebf7"));

        //padding for top, left, bottom, right
        addJLable(vmID, 2, 10, 2, 2, true);

        setJLable(totalPrice, btnWidth, btnHeight, true, Color.decode("#cfd0d1"), 1);
        setJLable(loc, btnWidth, btnHeight, true, Color.decode("#cfd0d1"), 1);
        setJLable(distance, btnWidth, btnHeight, true, Color.decode("#cfd0d1"), 1);

        addComponent(btn1, 0, 0, 0, 0, 1, 1, 0.5, GridBagConstraints.CENTER);
        addComponent(btn2, 10, 0, 2, 10, 4, 0, 0.5, GridBagConstraints.FIRST_LINE_END);
        addComponent(loc, 0, 0, 300, 150, 1, 1, 0.5, GridBagConstraints.CENTER);
        addComponent(distance, 0, 150, 300, 0, 1, 1, 0.5, GridBagConstraints.CENTER);
        addComponent(totalPrice, 0, 0, 120, 0, 1, 1, 0.5, GridBagConstraints.CENTER);

        btn1.addActionListener(this);
        btn2.addActionListener(this);

        btn1.setFocusable(false);
        btn2.setFocusable(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("PAY")) {
            this.dispose();
            new Window_4(controller, "prepayment");
        } else if (e.getActionCommand().equals("BACK")) {
            this.dispose();
            new Window_2(controller);
        }
    }

}

