import java.util.ArrayList;
import java.util.HashSet;

public class Hand {
    ArrayList<Card> cardList = new ArrayList<Card>();
    int sum = 0;
    boolean deng = false;
    int amount = 0;
    HashSet<String> ranks = new HashSet<String>();
    HashSet<String> suits = new HashSet<String>();

    public void addCard(Card card) {
        cardList.add(card);
        amount += 1;
        sum += card.getValue();
        ranks.add(card.getRank());
        suits.add(card.getSuit());
        calDeng();
    }

    private void calDeng() {
        if ((ranks.size() == 1) || (suits.size() == 1)) {
            deng = true;
        } else {
            deng = false;
        }
    }

    public void reset() {
        cardList.clear();
        sum = 0;
        deng = false;
        amount = 0;
        ranks.clear();
        suits.clear();
    }
}
