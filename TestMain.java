package JDialog;

public class TestMain {
	static Boolean flag = true;

	public static void main(String[] args) {
		Dlg dlg1 = new Dlg();
		Dlg dlg2 = new Dlg();
		Dlg dlg3 = new Dlg();
		
		dlg1.addBtn("OK");
//		dlg1.setDlg("SET ERROR MESSAGE");
		dlg1.setDlg(dlg1.getErrMsg());
//		dlg1.setVisDlg(true);
		
		dlg2.addBtn("OK");
//		dlg2.setDlg("SET SUCCESSFUL MESSAGE");
		dlg2.setDlg(dlg2.getSuccessMsg());
//		dlg2.setVisDlg(true);
		
		dlg3.setDlg("CHOOSE BETWEEN YES OR NO");
		dlg3.addBtn("YES");
		dlg3.addBtn("NO");
		dlg3.setVisDlg(true);
	}
}
