package ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class Window_7 extends JFrame implements ActionListener {

	Container frame = this.getContentPane();
	JButton btn1 = new JButton("LOGIN"); 
	JButton btn2 = new JButton("BACK");
	JTextField pwd = new JTextField(15);

	JPanel panel = new JPanel(new GridBagLayout());
	JLabel notice = new JLabel("<html><center>LOGIN PAGE<br>Please enter password<br>in the textfield below:</html>");
	JLabel vmID = new JLabel("          VM's ID           ");
	GridBagConstraints c = new GridBagConstraints();
    Border grayline = BorderFactory.createLineBorder(Color.decode("#cfd0d1"), 1);
    EmptyBorder eb = new EmptyBorder(new Insets(10, 10, 10, 10));

	int width = 500;
	int height = 500;

	public Window_7() {
		this("T2 OOPT DVM");
	}
	
	public Window_7(String title) {
		super(title);
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();
		this.setVisible(true);	
	}

	private void init() {
		notice.setPreferredSize(new Dimension(150,70));
		notice.setBorder(eb);
		//set limit for password
		pwd.setDocument(new JTextFieldLimit(10));

		frame.add(panel);	
		vmID.setBackground(Color.decode("#cfd0d1"));
		notice.setBackground(Color.decode("#cfd0d1"));
		panel.setBackground(Color.decode("#dcebf7"));

		//padding for top, left, bottom, right
		c.insets = new Insets(2,10,2,2);
		vmID.setOpaque(true);
		panel.add(vmID, c); 
		
		c.insets = new Insets(10,2,2,10); 
		c.anchor = GridBagConstraints.FIRST_LINE_END; //top corner right
		c.weightx = 0.5;
		c.gridx = 4;
		c.gridy = 0;
		panel.add(btn2, c);
		
		c.insets = new Insets(0,0,260,0); 
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER; //center 
		c.weighty = 0.5;
		notice.setOpaque(true);
		panel.add(notice, c);
			
		c.insets = new Insets(0,20,130,20); 
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER; //center
		c.weighty = 0.5;
		panel.add(pwd, c);
		
		c.insets = new Insets(0,0,50,5); 
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER; //center
		c.weighty = 0.5;
		panel.add(btn1, c);
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		
		btn1.setFocusable(false);
		btn2.setFocusable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "LOGIN") {
			dispose();
			Window_8 nextWindow = new Window_8("T2 OOPT DVM");
			
		}
		else if(e.getActionCommand() == "BACK") {
			dispose();
			Window_1 backWindow = new Window_1("T2 OOPT DVM");
		}
	}
}

