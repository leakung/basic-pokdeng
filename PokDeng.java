import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class PokDeng {
    private class Card {
        String rank;
        String suit;

        Card(String rank, String suit) {
            this.rank = rank;
            this.suit = suit;
        }

        public String toString() {
            return rank + "-" + suit;
        }

        public int getValue() {
            if ("JQK".contains(rank)) {
                return 10;
            } else if ("A".equals(rank)) {
                return 1;
            }
            return Integer.parseInt(rank);
        }

        public String getRank() {
            return rank;
        }

        public String getSuit() {
            return suit;
        }
    }

    private class Deck {
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

    private class Hand {
        ArrayList<Card> hand;
        int sum;
        boolean deng;
        int amount;
        HashSet<String> rank;
        HashSet<String> suit;

        Hand() {
            hand = new ArrayList<Card>();
            sum = 0;
            deng = false;
            amount = 0;
            rank = new HashSet<String>();
            suit = new HashSet<String>();
        }

        public void addCard(Card card) {
            hand.add(card);
            amount += 1;
            sum += card.getValue();
            rank.add(card.getRank());
            suit.add(card.getSuit());
            calDeng();
        }

        private void calDeng() {
            if ((rank.size() == 1) || (suit.size() == 1)) {
                deng = true;
            } else {
                deng = false;
            }
        }
    }

    private String compareCard(Hand dealer, Hand player) {
        String result = "";
        if ((dealer.sum % 10) > (player.sum % 10)) {
            result = "Dealer win";
            if (dealer.deng) {
                result += " with " + dealer.amount + " deng";
            }
        } else if ((dealer.sum % 10) < (player.sum % 10)) {
            result = "Player win";
            if (player.deng) {
                result += " with " + player.amount + " deng";
            }
        } else {
            result = "Tie";
        }
        return result;
    }

    public void startGame() {

        Deck deck = new Deck();
        deck.shuffle();

        Hand dealerHand = new Hand();
        Hand playerHand = new Hand();

        for (int i = 0; i < 2; i++) {
            playerHand.addCard(deck.callCard());
            dealerHand.addCard(deck.callCard());
        }

        System.out.println(playerHand.hand);

        Scanner sc = new Scanner(System.in);
        System.out.println("Press C for Call card or enter for Stay");
        String input = sc.nextLine();
        if ((input.equals("C")) || (input.equals("c"))) {
            playerHand.addCard(deck.callCard());
        }

        if (dealerHand.sum % 10 < 6) {
            dealerHand.addCard(deck.callCard());
        }
        
        System.out.println(dealerHand.hand);
        System.out.println(playerHand.hand);
        System.out.println(compareCard(dealerHand, playerHand));
    }

}
