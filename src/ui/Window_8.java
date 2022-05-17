package ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Window_8 extends JFrame implements ActionListener{

	Container frame = this.getContentPane();
	JButton btn1 = new JButton("NEXT"); 
	JButton btn2 = new JButton("BACK");
	JPanel panel = new JPanel();

	int width = 500;
	int height = 500;

	public Window_8() {
		this("T2 OOPT DVM");
	}
	
	public Window_8(String title) {
		super(title);
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();
		this.setVisible(true);	
	}

	private void init() {
		JLabel window = new JLabel("===========================This is Window-8========================");

		window.setHorizontalAlignment(JLabel.CENTER);
		
		panel.add(btn2, BorderLayout.CENTER);

		frame.add(window, BorderLayout.PAGE_START);
		frame.add(panel, BorderLayout.CENTER);	

		btn2.addActionListener(this);
		
		btn2.setFocusable(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "BACK") {
			dispose();
			Window_7 backWindow = new Window_7("T2 OOPT DVM");
		}
	}

}