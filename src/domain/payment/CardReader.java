package domain.payment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CardReader {
    private Map<String, Card> cards;

    public CardReader() {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("src/domain/payment/cardList.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert inputStream != null;
        Scanner in = new Scanner(inputStream);
        cards = new HashMap<>();
        while (in.hasNext()) {
            String[] argv = in.nextLine().split(" ", 3);
            cards.put(argv[0], new Card(argv[0], Integer.parseInt(argv[1]), Long.parseLong(argv[2])));
        }
    }

    public boolean checkCardValidity(String cardNum, int cardPwd) {
        Card card = cards.get(cardNum);
        if (card == null) {
            return false;
        }
        return card.getCardPwd() == cardPwd;
    }

    public Card getCardInfo(String cardNum) {
        return cards.get(cardNum);
    }
}