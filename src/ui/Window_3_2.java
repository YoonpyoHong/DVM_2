package ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import static ui.DvmWindow.*;

public class Window_3_2 extends JPanel implements ActionListener {
    private static final JButton btn1 = new JButton("PAY");
    private static final JButton btn2 = new JButton("BACK");

    private static final JLabel totalPrice = new JLabel("<html>Total price:<br><center>" + Window_2.getTotalPrice() + "</center></html>", SwingConstants.CENTER);
    private static final JLabel loc = new JLabel(String.format("Location: (%d, %d)", Window_2.dvmInfo[1], Window_2.dvmInfo[2]), SwingConstants.CENTER);
    private static final JLabel distance = new JLabel(String.format("Distance: %.1f", Math.sqrt(Window_2.dvmInfo[3])), SwingConstants.CENTER);

    private static final int btnWidth = 100;
    private static final int btnHeight = 70;

    JPanel panel;

    public Window_3_2() {
        init();
    }

    protected void init() {
        panel = new JPanel(new GridBagLayout());
        c = new GridBagConstraints();
        card.add(panel);
        panel.setBackground(Color.decode("#dcebf7"));

        addJLabel(panel);

        setJLabel(totalPrice, btnWidth, btnHeight, Color.decode("#cfd0d1"));
        setJLabel(loc, btnWidth, btnHeight, Color.decode("#cfd0d1"));
        setJLabel(distance, btnWidth, btnHeight, Color.decode("#cfd0d1"));

        addComponent(panel,btn1, 0, 0, 0, 0, 1, 1, 0.5, GridBagConstraints.CENTER);
        addComponent(panel,btn2, 10, 0, 2, 10, 4, 0, 0.5, GridBagConstraints.FIRST_LINE_END);
        addComponent(panel,loc, 0, 0, 300, 150, 1, 1, 0.5, GridBagConstraints.CENTER);
        addComponent(panel,distance, 0, 150, 300, 0, 1, 1, 0.5, GridBagConstraints.CENTER);
        addComponent(panel,totalPrice, 0, 0, 120, 0, 1, 1, 0.5, GridBagConstraints.CENTER);

        btn1.addActionListener(this);
        btn2.addActionListener(this);

        btn1.setFocusable(false);
        btn2.setFocusable(false);
    }

    public void actionPerformed(ActionEvent e) {
        card.removeAll();
        card.revalidate();
        card.repaint();

        if (e.getActionCommand().equals("PAY")) {
            card.add(new Window_4("prepayment"));
        } else if (e.getActionCommand().equals("BACK")) {
            card.add(new Window_2());
        }
    }

}

