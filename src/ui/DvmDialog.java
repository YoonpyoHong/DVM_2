package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DvmDialog implements ActionListener {
    private static final int DIALOG_WIDTH = 300;
    private static final int DIALOG_HEIGHT = 110;

    private JButton btn;

    private JDialog dlg;

    private JPanel panel;
    private JPanel panel2;

    DvmDialog(String str) {
        this(str, true);
    }

    DvmDialog(String str, Boolean dlgVisible) {
        JPanel outer = new JPanel(new BorderLayout());

        panel = new JPanel(); // FlowLayout
        outer.add(panel, BorderLayout.NORTH);

        panel2 = new JPanel(); // FlowLayout
        outer.add(panel2, BorderLayout.CENTER);

        addLabel(panel, str);

        dlg = new JDialog();
        dlg.setLayout(new FlowLayout());
        dlg.setLocationRelativeTo(null);
        dlg.setSize(DIALOG_WIDTH, DIALOG_HEIGHT);
        dlg.add(outer);
        addBtn("OK");

        dlg.setVisible(dlgVisible);
    }

    public void addLabel(JPanel panel, String str) {
        //set message to be displayed on JDialog
        panel.add(new JLabel(str), SwingConstants.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "OK") {
            dlg.setVisible(false);
        }
        if (e.getActionCommand() == "YES") {
            System.out.print("PROCEED");
            dlg.setVisible(false);
        }
        if (e.getActionCommand() == "NO") {
            System.out.print("DO NOT PROCEED");
            dlg.setVisible(false);
        }

        /*
         * if (e.getSource()== btn) do something;
         *
         *
         * */
    }

    public void addBtn(String string) {
        btn = new JButton(string);
        btn.addActionListener(this);
        btn.setFocusable(false);
        panel2.add(btn);
    }
}
