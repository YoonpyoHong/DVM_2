package domain.app;

import domain.payment.CardReader;

public class Main {
	public static void main(String[] args) {
		CardReader cardReader = new CardReader();
		cardReader.show();
		System.out.println(cardReader.checkCardValidity("0000-0000-0000-0000"));
		System.out.println(cardReader.checkCardValidity("1234-4567-3452-1262"));
//		new Window_1("T2 00PT DVM");
//		new Controller().turnMachineOn();
	}
}
