package JDialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Dlg implements ActionListener {

	JButton btn;
	JDialog dlg;
	JPanel panel;
	JPanel panel2;
	int width = 300;
	int height = 110;
	
	Dlg(){
		panel = new JPanel(); // FlowLayout
		panel2 = new JPanel(); // FlowLayout
		  
		JPanel outer = new JPanel(new BorderLayout());
		outer.add(panel, BorderLayout.NORTH);
		outer.add(panel2, BorderLayout.CENTER);
		
		dlg = new JDialog();  
        dlg.setLayout(new FlowLayout());  
		dlg.setLocationRelativeTo(null);
        dlg.setSize(width,height);            
        dlg.add(outer);
	}
	
	public void setDlg(String str) {
		//set message to be displayed on JDialog
    	panel.add(new JLabel(str), SwingConstants.CENTER);
    }
    
    public void setVisDlg(Boolean flag) {
    	//set JDialog visibility
    	if(flag) dlg.setVisible(true);  
    	else dlg.setVisible(false);  
    }

	@Override
	public void actionPerformed(ActionEvent e) {
	    if(e.getActionCommand() == "OK") {
	    	dlg.setVisible(false);
		}	
	    if(e.getActionCommand() == "YES") {
	    	System.out.print("PROCEED");
	    	dlg.setVisible(false);
		}	
	    if(e.getActionCommand() == "NO") {
	    	System.out.print("DO NOT PROCEED");
	    	dlg.setVisible(false);
		}	
	    
	    /*
	     * if(e.getSource()== btn) do something;
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
	
	public String getErrMsg() {
		return "ERROR";
	}
	
	public String getErrInPaymentMsg() {
		return "ERROR IN PAYMENT";
	}
	
	public String getErrInCardMsg() {
		return "ERROR IN CARD";
	}
	
	public String getSuccessMsg() {
		return "SUCCESS";
	}
	
	public String getSuccessfulTransactionMsg() {
		return "SUCCESSFUL TRANSACTION";
	}
	
	public String getDispenseMsg() {
		return "ITEM IS DISPENSED";
	}
}
