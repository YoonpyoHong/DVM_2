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

public class Window_5 extends JPanel implements ActionListener {

    private static final JButton btn1 = new JButton("HOME");
    private static final JButton btn2 = new JButton("BACK");
    private static final JLabel verCode = new JLabel("<html><center>Verification code:<br>1234567890</html>", SwingConstants.CENTER);
    JPanel panel;

    private static final JLabel loc = new JLabel("Location: (x,y)", SwingConstants.CENTER);
    private static final JLabel distance = new JLabel("Distance: m", SwingConstants.CENTER);

    public Window_5() {
        init();
    }

    protected void init() {
        panel = new JPanel(new GridBagLayout());
        c = new GridBagConstraints();

        card.add(panel);

        vmID.setBackground(Color.decode("#cfd0d1"));
        panel.setBackground(Color.decode("#dcebf7"));

        addJLabel(panel);
        setJLabel(verCode,120, 70, Color.decode("#cfd0d1"));
        setJLabel(loc,100, 70, Color.decode("#cfd0d1"));
        setJLabel(distance,100, 70, Color.decode("#cfd0d1"));

        addComponent(panel,btn2, 10, 0, 2, 10, 4, 0, 0.5, GridBagConstraints.FIRST_LINE_END);
        addComponent(panel,loc, 0, 0, 300, 150, 1, 1, 0.5, GridBagConstraints.CENTER);
        addComponent(panel,distance, 0, 150, 300, 0, 1, 1, 0.5, GridBagConstraints.CENTER);
        addComponent(panel,verCode, 0, 0, 120, 0, 1, 1, 0.5, GridBagConstraints.CENTER);
        addComponent(panel,btn1, 0, 0, 0, 0, 1, 1, 0.5, GridBagConstraints.CENTER);

        btn1.addActionListener(this);
        btn2.addActionListener(this);

        btn1.setFocusable(false);
        btn2.setFocusable(false);
    }

    public void actionPerformed(ActionEvent e) {
        card.removeAll();
        card.revalidate();
        card.repaint();

        if (e.getActionCommand().equals("HOME")) {
            card.add(new Window_1());
        } else if (e.getActionCommand().equals("BACK")) {
            card.add(new Window_4());
        }
    }
}
