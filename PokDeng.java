import java.util.Scanner;

public class PokDeng {

    private void showCard(Person dealer, Person player, int bet) {
        String resultText = "";
        int times = 1;
        if ((dealer.hand.getSum() % 10) > (player.hand.getSum() % 10)) {
            resultText = "Dealer win";
            if (dealer.hand.getDeng()) {
                resultText += " with " + dealer.hand.getCardAmount() + " deng";
                times = dealer.hand.getCardAmount();
            }
            pay(dealer, player, bet, times);
        } else if ((dealer.hand.getSum() % 10) < (player.hand.getSum() % 10)) {
            resultText = "Player win";
            if (player.hand.getDeng()) {
                resultText += " with " + player.hand.getCardAmount() + " deng";
                times = player.hand.getCardAmount();
            }
            pay(player, dealer, bet, times);
        } else {
            resultText = "Tie";
        }
        System.out.println(resultText);
    }

    private void pay(Person winner, Person loser, int bet, int times) {
        int payAmount = bet * times;
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
        System.out.println(dealer.pocket.getcoin());
        System.out.print("Player coin : ");
        System.out.println(player.pocket.getcoin());

        do {
            if (dealer.pocket.getcoin() < bet) {
                System.out.println("Dealer bankrupt");
                break;
            }
            if (player.pocket.getcoin() < bet) {
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
            System.out.println(player.hand.getCardList());
            System.out.println("Press C for Call card or enter for Stay");
            input = sc.nextLine();
            if ((input.equals("C")) || (input.equals("c"))) {
                player.hand.addCard(deck.callCard());
            }

            if (dealer.hand.getSum() % 10 < 6) {
                dealer.hand.addCard(deck.callCard());
            }

            System.out.print("Dealer card : ");
            System.out.println(dealer.hand.getCardList());
            System.out.print("Player card : ");
            System.out.println(player.hand.getCardList());
            showCard(dealer, player, bet);
            System.out.print("Dealer coin : ");
            System.out.println(dealer.pocket.getcoin());
            System.out.print("Player coin : ");
            System.out.println(player.pocket.getcoin());
            System.out.println("\nPress enter for play again or E for Exit");

            input = sc.nextLine();
            if ((input.equals("E")) || (input.equals("e"))) {
                break;
            }
        } while (true);
        sc.close();
    }
}
