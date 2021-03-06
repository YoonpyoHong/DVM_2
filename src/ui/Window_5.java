package ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Window_5 extends JFrame implements ActionListener{

	Container frame = this.getContentPane();
	JButton btn1 = new JButton("NEXT"); 
	JButton btn2 = new JButton("BACK");
	JPanel panel = new JPanel();

	int width = 500;
	int height = 500;

	public Window_5() {
		this("T2 OOPT DVM");
	}
	
	public Window_5(String title) {
		super(title);
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();
		this.setVisible(true);	
	}

	private void init() {
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
		if(e.getActionCommand() == "NEXT") {
			dispose();
			Window_6 nextWindow = new Window_6("T2 OOPT DVM");
			
		}
		else if(e.getActionCommand() == "BACK") {
			dispose();
			Window_4 backWindow = new Window_4("T2 OOPT DVM");
		}
	}

}
