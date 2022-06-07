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
        for (int cardNumPosition = 0; cardNumPosition < CARD_NUM_LENGTH; cardNumPosition++) {
            cardNum.append(inputCardNum.charAt(cardNumPosition));
            if (cardNumPosition % 4 == 3 && cardNumPosition != 15) {
                cardNum.append('-');
            }
        }
        return cardNum.toString();
    }

    private void loadCardList() {
        InputStream inputStream = null;
        try {
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert inputStream != null;
        Scanner cardDataInput = new Scanner(inputStream);
        cards = new HashMap<>();
        while (cardDataInput.hasNext()) {
            String[] cardList = cardDataInput.nextLine().split(" ", 3);
            Card card = new Card(cardList[0], Integer.parseInt(cardList[1]), Integer.parseInt(cardList[2]));
            cards.put(cardList[0], card);
            System.out.println(card + " has been added.");
        }
    }
}