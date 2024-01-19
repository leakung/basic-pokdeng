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

    public class Pocket {
        private int coin;

        Pocket() {
            coin = 0;
        }

        public void deposit(int coin) {
            this.coin += coin;
        }

        public void withdraw(int coin) {
            this.coin -= coin;
        }

        public int getcoin() {
            return this.coin;
        }
    }

    public class Person {
        Pocket pocket;
        Hand hand;

        Person() {
            pocket = new Pocket();
            hand = new Hand();
        }
    }

    private String compareCard(Person dealer, Person player, int bet) {
        String result = "";
        if ((dealer.hand.sum % 10) > (player.hand.sum % 10)) {
            result = "Dealer win";
            if (dealer.hand.deng) {
                result += " with " + dealer.hand.amount + " deng";
            }
            pay(dealer, player, bet);
        } else if ((dealer.hand.sum % 10) < (player.hand.sum % 10)) {
            result = "Player win";
            if (player.hand.deng) {
                result += " with " + player.hand.amount + " deng";
            }
            pay(player, dealer, bet);
        } else {
            result = "Tie";
        }
        return result;
    }

    private void pay(Person winner, Person loser, int bet) {
        int payAmount;
        if (winner.hand.deng) {
            payAmount = bet * winner.hand.amount;
        } else {
            payAmount = bet;
        }
        loser.pocket.withdraw(payAmount);
        winner.pocket.deposit(payAmount);
    }

    public void startGame() {

        Scanner sc = new Scanner(System.in);
        String input;
        Deck deck;
        int bet = 10;
        int coin = 100;
        Person dealer = new Person();
        Person player = new Person();

        dealer.pocket.deposit(coin);
        player.pocket.deposit(coin);

        System.out.print("Dealer coin : ");
        System.out.println(dealer.pocket.coin);
        System.out.print("Player coin : ");
        System.out.println(player.pocket.coin);

        do {

            if (dealer.pocket.coin <= 0) {
                System.out.println("Dealer bankrupt");
                break;
            }
            if (player.pocket.coin <= 0) {
                System.out.println("Player bankrupt");
                break;
            }

            deck = new Deck();
            deck.shuffle();
            dealer.hand = new Hand();
            player.hand = new Hand();

            for (int i = 0; i < 2; i++) {
                player.hand.addCard(deck.callCard());
                dealer.hand.addCard(deck.callCard());
            }

            System.out.print("\nPlayer card : ");
            System.out.println(player.hand.hand);
            System.out.println("Press C for Call card or enter for Stay");
            input = sc.nextLine();
            if ((input.equals("C")) || (input.equals("c"))) {
                player.hand.addCard(deck.callCard());
            }

            if (dealer.hand.sum % 10 < 6) {
                dealer.hand.addCard(deck.callCard());
            }

            System.out.print("Dealer card : ");
            System.out.println(dealer.hand.hand);
            System.out.print("Player card : ");
            System.out.println(player.hand.hand);
            System.out.println(compareCard(dealer, player, bet));
            System.out.print("Dealer coin : ");
            System.out.println(dealer.pocket.coin);
            System.out.print("Player coin : ");
            System.out.println(player.pocket.coin);
            System.out.println("\nPress enter for play again or E for Exit");

            input = sc.nextLine();
            if ((input.equals("E")) || (input.equals("e"))) {
                break;
            }
        } while (true);
        sc.close();
    }

}
