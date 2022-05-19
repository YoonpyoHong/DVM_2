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
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class Window_1 extends JFrame implements ActionListener{
		
	//20 drink list
	String[] drinkList = {"콜라", "사이다",
			"녹차", "홍차",
			"밀크티", "탄산수",
			"보리차", "캔커피",
			"물", "에너지드링크",
			"바닷물", "식혜",
			"아이스티", "딸기주스",
			"오렌지주스", "포도주스",
			"이온음료", "아메리카노",
			"핫초코", "카페라떼"}; 
	
	Container frame = this.getContentPane();
	JButton btn1 = new JButton("ADMIN LOGIN"); 
	JButton btn2 = new JButton("VERIFICATION CODE");
	JPanel panel = new JPanel(new GridBagLayout());
	JPanel itemLayout = new JPanel();
	GridBagConstraints c = new GridBagConstraints();
	JLabel vmID = new JLabel("          VM's ID           ");
	
	//padding for top, left, bottom, right
    EmptyBorder eb = new EmptyBorder(new Insets(20, 10, 0, 10));
    Border grayline = BorderFactory.createLineBorder(Color.decode("#cfd0d1"), 1);
	
	int frameWidth = 500;
	int frameHeight = 500;
	int btnWidth = 120;
	int btnHeight = 30;
	int drinkPanelWidth = 290;
	int drinkPanelHeight = 400;
	static int drinkPrice = 0;
	static String strPrice="";
	static String drinkName = "";
	
	public Window_1() {
		this("T2 OOPT DVM");
	}
	
	public Window_1(String title) {
		super(title);
		this.setSize(frameWidth, frameHeight);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();
		this.setVisible(true);	
	}

	private void init() {
		
		//set drink list layout's size
		itemLayout.setPreferredSize(new Dimension(drinkPanelWidth, drinkPanelHeight));

		//set color by hex code
		vmID.setBackground(Color.decode("#cfd0d1"));
		panel.setBackground(Color.decode("#dcebf7"));
		itemLayout.setBackground(Color.decode("#ebeced"));
		
		//set border + margin
        itemLayout.setBorder(BorderFactory.createCompoundBorder(grayline, eb));

        //add panel to frame
		frame.add(panel);
		
		//padding for top, left, bottom, right
		c.insets = new Insets(2,10,2,2); 

		vmID.setOpaque(true);
		panel.add(vmID, c);

		//padding for top, left, bottom, right
		c.insets = new Insets(10,2,2,10); 
		c.anchor = GridBagConstraints.FIRST_LINE_END; //top corner right
		c.weightx = 0.5;
		c.gridx = 4;
		c.gridy = 0;
		panel.add(btn1, c);
		
		c.gridx = 4;
		c.gridy = 4;
		c.anchor = GridBagConstraints.LINE_END; //center right
		c.weighty = 0.5;
		panel.add(btn2, c);

		btn1.addActionListener(this);	
		btn2.addActionListener(this);

		//remove border around text inside the button
		btn1.setFocusable(false);
		btn2.setFocusable(false);
		
		for(int i = 0; i < 20 ; i++) {
			JButton[] btn = new JButton[20];
			btn[i]= new JButton(drinkList[i]);
			btn[i].setFocusable(false);
			btn[i].setPreferredSize(new Dimension(btnWidth, btnHeight));
			itemLayout.add(btn[i]);
			btn[i].addActionListener(this);
		}
		
		c.anchor = GridBagConstraints.PAGE_START; //center left
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0,9,0,10); 
		c.gridwidth = 3;
		c.gridheight = 10;
		panel.add(itemLayout,c);
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "ADMIN LOGIN") {
			dispose();
			Window_7 nextWindow = new Window_7("T2 OOPT DVM");
		}
		else if(e.getActionCommand() == "VERIFICATION CODE") {
			dispose();
			Window_6 nextWindow = new Window_6("T2 OOPT DVM");
		}
		for(int i = 0; i < 20 ; i++) {
			if(e.getActionCommand() == drinkList[i]) {
				drinkName = drinkList[i];
				drinkPrice = (i + 1 ) * 100;
				dispose();
				Window_2 nextWindow = new Window_2("T2 OOPT DVM");
			}
		}
		
	}
	
	public static String getItemName() {
		if(drinkName!=null)
			return drinkName;
		else return null;
	}

	public static String getItemPrice() {
		if(drinkPrice!=0) {
			strPrice = Integer.toString(drinkPrice);
			return strPrice;
		}
		else return null;
	}
}
