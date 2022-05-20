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
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class Window_8 extends JFrame implements ActionListener{
		
	//random 7 drink list
	String[] itemList = new String[7];
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
	JButton btn1 = new JButton("LOGOUT"); 
	JButton btn2 = new JButton("UPDATE");
	JPanel panel = new JPanel(new GridBagLayout());
	JPanel itemLayout = new JPanel();
	JPanel itemLayout2 = new JPanel();
	JPanel itemLayout3 = new JPanel();
	JTextField[] itemQty = new JTextField[7];

	GridBagConstraints c = new GridBagConstraints();
	JLabel vmID = new JLabel("          VM's ID           ");
	
	//padding for top, left, bottom, right
    EmptyBorder eb = new EmptyBorder(new Insets(20, 10, 0, 10));
    Border grayline = BorderFactory.createLineBorder(Color.decode("#cfd0d1"), 1);
	
	int frameWidth = 500;
	int frameHeight = 500;
	int btnWidth = 120;
	int btnHeight = 30;
	int drinkPanelWidth = 350;
	int drinkPanelHeight = 400;
	static int drinkPrice = 0;
	static String strPrice="";
	static String drinkName = "";
	static int[] rand = genRandom();

	public Window_8() {
		this("T2 OOPT DVM");
	}
	
	public Window_8(String title) {
		super(title);
		this.setSize(frameWidth, frameHeight);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();
		this.setVisible(true);	
	}

	private void init() {
		//generate 7 item list randomly for every dvm
		for(int i=0; i<rand.length;i++) {
			itemList[i] = drinkList[rand[i]];
		}
		
		//set drink list layout's size
		itemLayout.setPreferredSize(new Dimension(drinkPanelWidth, drinkPanelHeight));
		itemLayout2.setPreferredSize(new Dimension(150,250));
		itemLayout3.setPreferredSize(new Dimension(200,250));
		
		//set color by hex code
		vmID.setBackground(Color.decode("#cfd0d1"));
		panel.setBackground(Color.decode("#dcebf7"));
		
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
		
		for(int i = 0; i < 7 ; i++) {
			itemQty[i]= new JTextField();
			itemQty[i].setPreferredSize(new Dimension(btnWidth+50, btnHeight));
			itemQty[i].setDocument(new JTextFieldLimit(2));
			itemLayout3.add(itemQty[i], BorderLayout.CENTER);
		}
		
		for(int i = 0; i < 7 ; i++) {
			JLabel[] btn = new JLabel[7];
			btn[i]= new JLabel(itemList[i],SwingConstants.CENTER);
			btn[i].setPreferredSize(new Dimension(btnWidth, btnHeight));
			btn[i].setOpaque(true);
			btn[i].setBackground(Color.WHITE);
			btn[i].setBorder(BorderFactory.createLineBorder(Color.decode("#cfd0d1"), 1));
			itemLayout2.add(btn[i], BorderLayout.CENTER);
		}
		
		c.anchor = GridBagConstraints.LINE_START; //right
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0,15,0,0); 
		c.gridwidth = 0;
		c.gridheight = 0;
		panel.add(itemLayout3,c);
		
		c.anchor = GridBagConstraints.LINE_END; //right
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0,0,0,125); 
		c.gridwidth = 0;
		c.gridheight = 0;
		panel.add(itemLayout2,c);
		
		c.anchor = GridBagConstraints.PAGE_START; //center left
		c.gridx = 0;
		c.gridy = 1; 
		c.insets = new Insets(0,6,0,10); 
		c.gridwidth = 3;
		c.gridheight = 10;
		panel.add(itemLayout,c);
				
	}
	

	private static int[] genRandom() {
		// generate random number from 0 to 20
		Random rand = new Random();
		int[] temp = new int[7];
		
		for(int i=0; i<temp.length; i++) {
			temp[i] = rand.nextInt(20); //0~19
			for(int j=0; j<i; j++) {
				if (temp[i] == temp[j]) {
					i--; //no repetition of same number
					break;
				}
			}
		}
		return temp;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "LOGOUT") {
			//do nothing
			dispose();
			Window_1 nextWindow = new Window_1("T2 OOPT DVM");
		}
		else if(e.getActionCommand() == "UPDATE") {
//			below is unimplemented code:
//			i don't know if it will work,
//			but the idea is to get the item's name
//			every time admin click the button update
//			and update its quantity in the local storage
			
			for(int i = 0; i < itemList.length ; i++) {
				itemQty[i].getText();
			
				System.out.println(itemQty[i].getText());
//				if there is a non-integer character,
//				show JDialog (Please input only integer);
				
//				clear text field
				itemQty[i].setText("");
			}
//			update data //
			
//			show JDialog (successful update)
//			for 15 second
						
		}
	}
}
