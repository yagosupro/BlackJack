package entity;

public class Card {

    private Rank rank;
    private Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getRankValue() {
        return rank.getValue();
    }

    public String getFace() {
        return rank.getFace();
    }

    public Suit getSuit() {
        return suit;
    }


}
