package ui;

import domain.Controller;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Window_3_1 extends DvmWindow {
    private static final JButton btn1 = new JButton("PAY");
    private static final JButton btn2 = new JButton("BACK");

    private static final JLabel totalPrice = new JLabel("<html>Total price:<br><center>" + Window_2.getTotalPrice() + "</center></html>", SwingConstants.CENTER);

    private static final int btnTotalPriceWidth = 100;
    private static final int btnTotalPriceHeight = 70;

    public Window_3_1(Controller controller) {
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

        setJLable(totalPrice, btnTotalPriceWidth, btnTotalPriceHeight, true, Color.decode("#cfd0d1"), 1);

        addComponent(btn1, 0, 55, 0, 0, 1, 1, 0.5, GridBagConstraints.CENTER);
        addComponent(btn2, 10, 2, 2, 10, 4, 0, 0.5, GridBagConstraints.FIRST_LINE_END);
        addComponent(totalPrice, 0, 50, 120, 0, 1, 1, 0.5, GridBagConstraints.CENTER);

        btn1.addActionListener(this);
        btn2.addActionListener(this);

        btn1.setFocusable(false);
        btn2.setFocusable(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("PAY")) {
            this.dispose();
            new Window_4(controller, "payment");
        } else if (e.getActionCommand().equals("BACK")) {
            this.dispose();
            new Window_2(controller);
        }
    }
}

