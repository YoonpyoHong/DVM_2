package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DvmDialog implements ActionListener {
    private static final int DIALOG_WIDTH = 300;
    private static final int DIALOG_HEIGHT = 110;

    private final JDialog dlg;

    DvmDialog(String str) {
        this(str, true);
    }

    DvmDialog(String str, Boolean dlgVisible) {
        JPanel outer = new JPanel(new BorderLayout());

        JPanel panel = new JPanel(); // FlowLayout
        outer.add(panel, BorderLayout.NORTH);

        JPanel panel2 = new JPanel(); // FlowLayout
        outer.add(panel2, BorderLayout.CENTER);

        addLabel(panel, str);

        dlg = new JDialog();
        dlg.setLayout(new FlowLayout());
        dlg.setLocationRelativeTo(null);
        dlg.setSize(DIALOG_WIDTH, DIALOG_HEIGHT);
        dlg.add(outer);
        addBtn(panel2, "OK");

        dlg.setVisible(dlgVisible);
    }

    public void addLabel(JPanel panel, String str) {
        //set message to be displayed on JDialog
        panel.add(new JLabel(str), SwingConstants.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("OK")) {
            dlg.setVisible(false);
        } else if (e.getActionCommand().equals("YES")) {
            System.out.print("PROCEED");
            dlg.setVisible(false);
        } else if (e.getActionCommand().equals("NO")) {
            System.out.print("DO NOT PROCEED");
            dlg.setVisible(false);
        }
        /*
         * if (e.getSource()== btn) do something;
         *
         *
         * */
    }

    public void addBtn(JPanel panel, String string) {
        JButton btn = new JButton(string);
        btn.addActionListener(this);
        btn.setFocusable(false);
        panel.add(btn);
    }
}
