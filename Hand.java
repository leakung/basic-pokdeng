import java.util.ArrayList;
import java.util.HashSet;

public class Hand {
    private ArrayList<Card> cardList = new ArrayList<Card>();
    private int sum = 0;
    private boolean deng = false;
    private int cardAmount = 0;
    private HashSet<String> ranks = new HashSet<String>();
    private HashSet<String> suits = new HashSet<String>();

    public void addCard(Card card) {
        cardList.add(card);
        cardAmount += 1;
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
        cardAmount = 0;
        ranks.clear();
        suits.clear();
    }

    public ArrayList<Card> getCardList() {
        return cardList;
    }
    
    public int getSum() {
        return sum;
    }

    public boolean getDeng() {
        return deng;
    }

    public int getCardAmount() {
        return cardAmount;
    }

    public HashSet<String> getRanks() {
        return ranks;
    }
    
    public HashSet<String> getSuits() {
        return suits;
    }
}
