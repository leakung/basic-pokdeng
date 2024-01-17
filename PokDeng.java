import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class PokDeng {
    private class Card {
        String value;
        String type;

        Card(String value, String type) {
            this.value = value;
            this.type = type;
        }

        public String toString() {
            return value + "-" + type;
        }

        public int getValue() {
            if ("JQK".contains(value)) {
                return 10;
            } else if ("A".equals(value)) {
                return 1;
            }
            return Integer.parseInt(value);
        }

        public String getType() {
            return type;
        }
    }

    ArrayList<Card> deck;
    ArrayList<Card> dealerHand;
    int dealerSum;
    HashSet<Integer> dealerValue;
    HashSet<String> dealerType;
    boolean dealerDeng;
    ArrayList<Card> playerHand;
    int playerSum;
    HashSet<Integer> playerValue;
    HashSet<String> playerType;
    boolean playerDeng;
    String result;

    PokDeng() {
        startGame();
    }

    public void startGame() {
        // deck
        buildDeck();
        shuffleDeck();

        dealerHand = new ArrayList<Card>();
        dealerSum = 0;
        dealerValue = new HashSet<Integer>();
        dealerType = new HashSet<String>();
        dealerDeng = false;
        playerHand = new ArrayList<Card>();
        playerSum = 0;
        playerValue = new HashSet<Integer>();
        playerType = new HashSet<String>();
        playerDeng = false;
        result = "";

        // play
        dealCard();
        // cal
        calCard();
        // compare
        compareCard();

        System.out.println("RESULT");
        System.out.println(result);
    }

    public void buildDeck() {
        deck = new ArrayList<Card>();
        String[] values = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
        String[] types = { "C", "D", "H", "S" };
        for (int j = 0; j < types.length; j++) {
            for (int i = 0; i < values.length; i++) {
                Card card = new Card(values[i], types[j]);
                deck.add(card);
            }
        }
    }

    public void shuffleDeck() {
        for (int i = 0; i < deck.size(); i++) {
            Random random = new Random();
            int j = random.nextInt(deck.size());
            Card curCard = deck.get(i);
            Card ranCard = deck.get(j);
            deck.set(i, ranCard);
            deck.set(j, curCard);
        }
    }

    public void dealCard() {
        Card card;
        for (int i = 0; i < 2; i++) {
            card = deck.remove(deck.size() - 1);
            playerHand.add(card);
            card = deck.remove(deck.size() - 1);
            dealerHand.add(card);
        }
        System.out.println("DEALER:");
        System.out.println(dealerHand);
        System.out.println("PLAYER:");
        System.out.println(playerHand);
    }

    public void calCard() {
        for (int i = 0; i < dealerHand.size(); i++) {
            dealerSum += dealerHand.get(i).getValue();
            dealerValue.add(dealerHand.get(i).getValue());
            dealerType.add(dealerHand.get(i).getType());
        }
        for (int i = 0; i < playerHand.size(); i++) {
            playerSum += playerHand.get(i).getValue();
            playerValue.add(playerHand.get(i).getValue());
            playerType.add(playerHand.get(i).getType());
        }
        if ((dealerType.size() == 1) || (dealerValue.size() == 1)) {
            dealerDeng = true;
        }
        if ((playerType.size() == 1) || (playerValue.size() == 1)) {
            playerDeng = true;
        }
    }

    public void compareCard() {
        if ((dealerSum % 10) > (playerSum % 10)) {
            result = "Dealer win";
            if (dealerDeng) {
                result += " with deng";
            }
        } else if ((dealerSum % 10) < (playerSum % 10)) {
            result = "Player win";
            if (playerDeng) {
                result += " with deng";
            }
        } else {
            result = "Tie";
        }

    }
}
