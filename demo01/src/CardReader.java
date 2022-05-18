import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class CardReader {
    private Map<String, Card> cards;

    public CardReader() {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("cardList.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner in = new Scanner(inputStream);
        cards = new HashMap<String, Card>();
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