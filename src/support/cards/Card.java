package support.cards;

import javax.swing.ImageIcon;

public class Card implements Comparable<Card> {

    public enum Rank {Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace}

    public enum Suit {Club, Diamond, Heart, Spade}

    protected final Rank rank;
    protected final Suit suit;
    protected ImageIcon image;

    public Card(Rank rank, Suit suit, ImageIcon image) {
        this.rank = rank;
        this.suit = suit;
        this.image = image;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public ImageIcon getImage() {
        return image;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        else if (obj == null || obj.getClass() != this.getClass())
            return false;
        else {
            Card c = (Card) obj;
            return (this.rank == c.rank);
        }
    }

    @Override
    public int compareTo(Card other) {
        return this.rank.compareTo(other.rank);
    }

    @Override
    public String toString() {
        return suit + " " + rank;
    }
}
