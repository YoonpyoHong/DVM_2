package domain.app;

import domain.message.MessageManager;
import domain.payment.CardReader;
import domain.payment.VerificationManager;
import ui.Window_1;

public class Main {
	public static void main(String[] args) {
		testMessageManager();
//		testUI();
//		testVerificationManager();
	}

	private static void testMessageManager() {
		Controller controller = new Controller();
		MessageManager msgManager = controller.getMsgManager();
		msgManager.checkStockOfOtherVM(1, 2);
	}

	private static void testCardReader() {
		CardReader cardReader = new CardReader();
	}

	private static void testVerificationManager() {
		VerificationManager vManager = new VerificationManager();
		for (int i = 0; i < 10; i++) {
			String code = vManager.createVerificationCode();
			System.out.println("code = " + code);
		}
	}

	private static void testUI() {
		new Window_1("T2 00PT DVM");
	}

	private static void testController() {
//		new Controller().turnMachineOn();
	}
}