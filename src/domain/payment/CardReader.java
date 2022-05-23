package domain.payment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static domain.payment.Card.CARD_NUM_LENGTH;

public class CardReader {
    private static Map<String, Card> cards;

    public CardReader() {
        loadCardList();
    }

    public boolean checkCardValidity(String cardNum, int cardPwd) {
        System.err.println("cardNum = " + cardNum);
        Card card = cards.get(cardNum);
        if (card == null) {
            return false;
        }
        System.err.println("matched card = " + card);
        return card.getCardPwd() == cardPwd;
    }

    public Card getCardInfo(String cardNum) {
        return cards.get(cardNum);
    }

    public String encodeCardNum(String inputCardNum) {
        String cardNum = "";
        for (int i = 0; i < CARD_NUM_LENGTH; i++) {
            cardNum += inputCardNum.charAt(i);
            if (i % 4 == 3 && i != 15) {
                cardNum += '-';
            }
        }
        return cardNum;
    }

    private void loadCardList() {
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
}