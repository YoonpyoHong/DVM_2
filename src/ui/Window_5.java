package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Window_5 extends DvmWindow {

	private static final JButton btn1 = new JButton("HOME"); 
	private static final JButton btn2 = new JButton("BACK");
	private static final JLabel verCode = new JLabel("<html><center>Verification code:<br>1234567890</html>",SwingConstants.CENTER);
//	private static final JLabel time = new JLabel("<html>Time runout display<br><center>(60 sec)</center></html>",SwingConstants.CENTER);

	private static final JLabel loc = new JLabel("Location: (x,y)", SwingConstants.CENTER);
	private static final JLabel distance = new JLabel("Distance: m",SwingConstants.CENTER);
	
	 public Window_5(Controller controller) {
	        super(controller);
	 }

	 @Override
	 protected void init() {
        panel = new JPanel(new GridBagLayout());
        c = new GridBagConstraints();

        frame.add(panel);
        
		vmID.setBackground(Color.decode("#cfd0d1"));
		panel.setBackground(Color.decode("#dcebf7"));

		//padding for top, left, bottom, right
		c.insets = new Insets(2,10,2,2);
		vmID.setOpaque(true);
		panel.add(vmID, c); 
		
//		time.setPreferredSize(new Dimension(120, 50));
//		time.setOpaque(true);
//		time.setBorder(BorderFactory.createLineBorder(Color.decode("#cfd0d1"), 1));
//		
		verCode.setPreferredSize(new Dimension(120, 70));
		verCode.setOpaque(true);
		verCode.setBorder(BorderFactory.createLineBorder(Color.decode("#cfd0d1"), 1));
		
		loc.setPreferredSize(new Dimension(100, 70));
		loc.setOpaque(true);
		loc.setBorder(BorderFactory.createLineBorder(Color.decode("#cfd0d1"), 1));
		
		distance.setPreferredSize(new Dimension(100, 70));
		distance.setOpaque(true);
		distance.setBorder(BorderFactory.createLineBorder(Color.decode("#cfd0d1"), 1));
		
		c.insets = new Insets(10,0,2,10); 
		c.anchor = GridBagConstraints.FIRST_LINE_END; //top corner right
		c.weightx = 0.5;
		c.gridx = 4;
		c.gridy = 0;
		panel.add(btn2, c);
		
		c.insets = new Insets(0,0,300,150); 
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER; //center
		c.weighty = 0.5;
		panel.add(loc, c);
		
		c.insets = new Insets(0,150,300,0); 
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER; //center
		c.weighty = 0.5;
		panel.add(distance, c);
		
		
		c.insets = new Insets(0,0,120,0); 
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER; //center
		c.weighty = 0.5;
		panel.add(verCode, c);
		
		c.insets = new Insets(0,0,0,0); 
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER; //center
		c.weighty = 0.5;
		panel.add(btn1, c);
		
//		c.insets = new Insets(0,10,10,0); 
//		c.gridx = 0;
//		c.gridy = 1;
//		c.anchor = GridBagConstraints.LAST_LINE_START; //bottom left
//		c.weighty = 0.5;
//		panel.add(time, c);
//		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		
		btn1.setFocusable(false);
		btn2.setFocusable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//     	if(btn1.getActionListeners()!=null) {
//         	btn1.removeActionListener(this);
//         }
//     	if(btn2.getActionListeners()!=null) {
//         	btn2.removeActionListener(this);
//         }
        if (e.getActionCommand().equals("HOME")) {
            dispose();
            new Window_1(controller);
        } else if (e.getActionCommand().equals("BACK")) {
            dispose();
            new Window_4(controller);
        }
    }
}
