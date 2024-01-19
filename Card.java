public class Card {
    private String rank;
    private String suit;

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
