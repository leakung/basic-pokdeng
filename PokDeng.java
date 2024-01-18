import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

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
    boolean dealerDeng;
    ArrayList<Card> playerHand;
    int playerSum;
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
        dealerDeng = false;
        playerHand = new ArrayList<Card>();
        playerSum = 0;
        playerDeng = false;
        result = "";

        // play
        dealCard();

        System.out.print("\nPLAYER : ");
        System.out.println(playerHand);

        Scanner sc = new Scanner(System.in);
        System.out.println("Press C for Call card or enter for Stay");
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if ((input.equals("C")) || (input.equals("c"))) {
                callCard();
                break;
            } else {
                break;
            }
        }

        // cal
        calCard();

        // compare
        compareCard();

        System.out.print("\nDEALER : ");
        System.out.println(dealerHand);
        System.out.print("PLAYER : ");
        System.out.println(playerHand);
        System.out.print("\nRESULT : ");
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
    }

    public void calCard() {
        HashSet<Integer> dealerValue = new HashSet<Integer>();
        HashSet<String> dealerType = new HashSet<String>();
        HashSet<Integer> playerValue = new HashSet<Integer>();
        HashSet<String> playerType = new HashSet<String>();

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
                result += " with " + dealerHand.size() + " deng";
            }
        } else if ((dealerSum % 10) < (playerSum % 10)) {
            result = "Player win";
            if (playerDeng) {
                result += " with " + playerHand.size() + " deng";
            }
        } else {
            result = "Tie";
        }
    }

    public void callCard() {
        Card card = deck.remove(deck.size() - 1);
        playerHand.add(card);
    }
}
