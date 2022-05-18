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
            String[] argv = in.nextLine().split(" ", 2);
            cards.put(argv[0], new Card(argv[0], Long.parseLong(argv[1])));
        }
    }

    public Boolean checkCardValidity(String card_num) {
        return cards.containsKey(card_num);
    }

    public void show() {
        cards.forEach((key, value) -> System.out.println(value.getCardNum() + " " + value.getMoney()));
    }
}