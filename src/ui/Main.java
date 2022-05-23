package ui3;

public class Main {
	public static void main(String[] args) throws InterruptedException {
//		testMessageManager();
		testUI();
//		testVerificationManager();
	}

	private static void testMessageManager() throws InterruptedException {
		Controller controller = new Controller();
//		MessageManager msgManager = controller.getMsgManager();
//		msgManager.checkStockOfOtherVM(1, 2);
	}

	private static void testCardReader() {
//		CardReader cardReader = new CardReader();
	}

	private static void testVerificationManager() {
//		VerificationManager vManager = new VerificationManager(); //		for (int i = 0; i < 10; i++) {
//			String code = vManager.createVerificationCode();
//			System.out.println("code = " + code);
//		}
	}

	private static void testUI() {
		new Window_1(new Controller());
	}

	private static void testController() {
//		new Controller().turnMachineOn();
	}
}