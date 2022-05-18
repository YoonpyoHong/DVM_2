package domain.app;

import domain.payment.CardReader;
import domain.payment.VerificationManager;
import ui.Window_1;

public class Main {
	public static void main(String[] args) {
//		testVerificationManager();
	}

	private static void testCardReader() {
		CardReader cardReader = new CardReader();
		cardReader.show();
		System.out.println(cardReader.checkCardValidity("0000-0000-0000-0000"));
		System.out.println(cardReader.checkCardValidity("1234-4567-3452-1262"));
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
