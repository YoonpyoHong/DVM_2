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
        System.out.println(this.getClass() + " created.");
    }

    public boolean checkCardValidity(String cardNum, int cardPwd) {
        System.out.println("cardNum = " + cardNum);
        Card card = cards.get(cardNum);
        if (card == null) {
            return false;
        }
        System.out.println("matched card = " + card);
        return card.getCardPwd() == cardPwd;
    }

    public Card getCardInfo(String cardNum) {
        return cards.get(cardNum);
    }

    public String encodeCardNum(String inputCardNum) {
        StringBuilder cardNum = new StringBuilder();
        for (int i = 0; i < CARD_NUM_LENGTH; i++) {
            cardNum.append(inputCardNum.charAt(i));
            if (i % 4 == 3 && i != 15) {
                cardNum.append('-');
            }
        }
        return cardNum.toString();
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
            Card card = new Card(argv[0], Integer.parseInt(argv[1]), Integer.parseInt(argv[2]));
            cards.put(argv[0], card);
            System.out.println(card + " has been added.");
        }
    }
}