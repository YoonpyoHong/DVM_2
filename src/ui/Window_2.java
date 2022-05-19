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
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class Window_2 extends JFrame implements ActionListener {

	Container frame = this.getContentPane();
	JButton btn1 = new JButton("NEXT"); 
	JButton btn2 = new JButton("BACK");
	JButton add = new JButton("+");
	JButton minus = new JButton("-");
	
	JPanel panel = new JPanel(new GridBagLayout());
	JLabel vmID = new JLabel("          VM's ID           ");
	GridBagConstraints c = new GridBagConstraints();
    Border grayline = BorderFactory.createLineBorder(Color.decode("#cfd0d1"), 1);

    Integer[] num = new Integer[999];
	static JLabel itemQuantity = new JLabel("1",SwingConstants.CENTER);
	static JLabel itemPrice = new JLabel(Window_1.getItemPrice(), SwingConstants.CENTER);

	int width = 500;
	int height = 500;
	static int totalPrice = 0;
	static String strPrice="";
	int j;
	
	public Window_2() {
		this("T2 OOPT DVM");
	}
	
	public Window_2(String title) {
		super(title);
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();
		this.setVisible(true);	
	}

	private void init() {
		j=0;
		itemQuantity.setText("1"); 
		
		for(int i=0;i<999;i++) {
			num[i] = i;
		}
		frame.add(panel);	
		vmID.setBackground(Color.decode("#cfd0d1"));
		panel.setBackground(Color.decode("#dcebf7"));

		//padding for top, left, bottom, right
		c.insets = new Insets(2,10,2,2);
		vmID.setOpaque(true);
		panel.add(vmID, c); 
		
		itemQuantity.setPreferredSize(new Dimension(width - 400, height - 450));
		itemQuantity.setOpaque(true);
		itemQuantity.setBorder(BorderFactory.createLineBorder(Color.decode("#cfd0d1"), 1));
		
		JLabel itemName = new JLabel(Window_1.getItemName(),SwingConstants.CENTER);
		itemName.setPreferredSize(new Dimension(width - 370, height - 450));
		itemName.setOpaque(true);
		itemName.setBorder(BorderFactory.createLineBorder(Color.decode("#cfd0d1"), 1));

		itemPrice.setPreferredSize(new Dimension(width - 370, height - 450));
		itemPrice.setOpaque(true);
		itemPrice.setBorder(BorderFactory.createLineBorder(Color.decode("#cfd0d1"), 1));

		c.insets = new Insets(10,2,2,10); 
		c.anchor = GridBagConstraints.FIRST_LINE_END; //top corner right
		c.weightx = 0.5;
		c.gridx = 4;
		c.gridy = 0;
		panel.add(btn2, c);
		
		c.insets = new Insets(0,0,250,150); 
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER; //center 
		c.weighty = 0.5;
		panel.add(itemName, c);
		
		c.insets = new Insets(0,120,250,0); 
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER; //center 
		c.weighty = 0.5;
		panel.add(itemPrice, c);
		
		c.insets = new Insets(0,0,120,180); 
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER; //center
		c.weighty = 0.5;
		panel.add(add, c);
		
		c.insets = new Insets(0,0,120,20); 
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER; //center
		c.weighty = 0.5;
		panel.add(itemQuantity, c);
		
		c.insets = new Insets(0,137,120,0); 
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER; //center
		c.weighty = 0.5;
		panel.add(minus, c);
		
		c.insets = new Insets(0,0,0,15); 
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER; //center
		c.weighty = 0.5;
		panel.add(btn1, c);
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		add.addActionListener(this);
		minus.addActionListener(this);
		
		btn1.setFocusable(false);
		btn2.setFocusable(false);
		add.setFocusable(false);
		minus.setFocusable(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "NEXT") {
			dispose();
			Window_3_1 nextWindow = new Window_3_1("T2 OOPT DVM");
			
		}
		else if(e.getActionCommand() == "BACK") {
			dispose();
			Window_1 backWindow = new Window_1("T2 OOPT DVM");
		}
		else if(e.getActionCommand() == "+") {
			if(j==999) itemQuantity.setText("999"); 
			else{itemQuantity.setText(Integer.toString(num[++j]));}
		}
		else if(e.getActionCommand() == "-") {
			if(j==0) itemQuantity.setText("0"); 
			else{itemQuantity.setText(Integer.toString(num[--j]));}
		}
	}
	
	static String getTotalPrice() {
		totalPrice = Integer.parseInt(itemQuantity.getText()) * Integer.parseInt(itemPrice.getText());
		strPrice = Integer.toString(totalPrice);
		return strPrice;
	}

}

