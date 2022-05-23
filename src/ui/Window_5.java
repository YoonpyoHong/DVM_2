package ui;

import domain.app.Controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Window_5 extends DvmWindow {
    private static final JButton btn1 = new JButton("NEXT");
    private static final JButton btn2 = new JButton("BACK");

    public Window_5(Controller controller) {
        super(controller);
    }

    protected void init() {
        panel = new JPanel();
        JLabel window = new JLabel("===========================This is Window-5========================");

        window.setHorizontalAlignment(JLabel.CENTER);

        panel.add(btn1, BorderLayout.CENTER);
        panel.add(btn2, BorderLayout.CENTER);

        frame.add(window, BorderLayout.PAGE_START);
        frame.add(panel, BorderLayout.CENTER);

        btn1.addActionListener(this);
        btn2.addActionListener(this);

        btn1.setFocusable(false);
        btn2.setFocusable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("NEXT")) {
            this.dispose();
            new Window_6(controller);
        } else if (e.getActionCommand().equals("BACK")) {
            this.dispose();
            new Window_4(controller);
        }
    }
}
