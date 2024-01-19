import java.util.ArrayList;
import java.util.Random;

public class Deck {
    ArrayList<Card> deck;
    Card card;

    Deck() {
        deck = new ArrayList<Card>();
        build();
    }

    private void build() {
        String[] ranks = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
        String[] suits = { "C", "D", "H", "S" };
        for (int j = 0; j < suits.length; j++) {
            for (int i = 0; i < ranks.length; i++) {
                card = new Card(ranks[i], suits[j]);
                deck.add(card);
            }
        }
    }

    public void shuffle() {
        for (int i = 0; i < deck.size(); i++) {
            Random random = new Random();
            int j = random.nextInt(deck.size());
            Card curCard = deck.get(i);
            Card ranCard = deck.get(j);
            deck.set(i, ranCard);
            deck.set(j, curCard);
        }
    }

    public Card callCard() {
        card = this.deck.remove(deck.size() - 1);
        return card;
    }
}
