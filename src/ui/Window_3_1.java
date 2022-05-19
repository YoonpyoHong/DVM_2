package ui;

import java.awt.BorderLayout;
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
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class Window_3_1 extends JFrame implements ActionListener{

	Container frame = this.getContentPane();
	JButton btn1 = new JButton("PAY"); 
	JButton btn2 = new JButton("BACK");
	JPanel panel = new JPanel(new GridBagLayout());
	JLabel vmID = new JLabel("          VM's ID           ");
	GridBagConstraints c = new GridBagConstraints();
    Border grayline = BorderFactory.createLineBorder(Color.decode("#cfd0d1"), 1);

	JLabel totalPrice = new JLabel("<html>Total price:<br><center>" + Window_2.getTotalPrice() +"</center></html>",SwingConstants.CENTER);

	int width = 500;
	int height = 500;

	public Window_3_1() {
		this("T2 OOPT DVM");
	}
	
	public Window_3_1(String title) {
		super(title);
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();
		this.setVisible(true);	
	}

	private void init() {
		frame.add(panel);	
		vmID.setBackground(Color.decode("#cfd0d1"));
		panel.setBackground(Color.decode("#dcebf7"));

		//padding for top, left, bottom, right
		c.insets = new Insets(2,10,2,2);
		vmID.setOpaque(true);
		panel.add(vmID, c); 
		
		totalPrice.setPreferredSize(new Dimension(width - 400, height - 430));
		totalPrice.setOpaque(true);
		totalPrice.setBorder(BorderFactory.createLineBorder(Color.decode("#cfd0d1"), 1));
		
		c.insets = new Insets(10,2,2,10); 
		c.anchor = GridBagConstraints.FIRST_LINE_END; //top corner right
		c.weightx = 0.5;
		c.gridx = 4;
		c.gridy = 0;
		panel.add(btn2, c);
		
		c.insets = new Insets(0,50,120,0); 
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER; //center
		c.weighty = 0.5;
		panel.add(totalPrice, c);
		
		c.insets = new Insets(0,55,0,0); 
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
		if(e.getActionCommand() == "PAY") {
			dispose();
			Window_4 nextWindow = new Window_4("T2 OOPT DVM");
			
		}
		else if(e.getActionCommand() == "BACK") {
			dispose();
			Window_2 backWindow = new Window_2("T2 OOPT DVM");
		}
	}

}

