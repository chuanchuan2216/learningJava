package support.cards;

import ch06.lists.ArrayBoundedList;

import javax.swing.*;
import java.util.Iterator;
import java.util.Random;

public class CradDeck {

    public static final int NUMCARDS = 52;

    protected ArrayBoundedList<Card> deck;
    protected Iterator<Card> deal;

    public CradDeck() {
        deck = new ArrayBoundedList<Card>(NUMCARDS);
        ImageIcon image;
        for (Card.Suit suit : Card.Suit.values())
            for (Card.Rank rank : Card.Rank.values()) {
                image = new ImageIcon("src/support/cards/images/" + suit + "_" + rank + "_RA.gif");
                deck.add(new Card(rank, suit, image));
            }
        deal = deck.interator();
    }

    public void shuffle() {
        Random rand = new Random();
        int randLoc;
        Card temp;

        for (int i = (NUMCARDS - 1); i > 0; i--) {
            randLoc = rand.nextInt(i);
            temp = deck.get(randLoc);
            deck.set(randLoc, deck.get(i));
            deck.set(i, temp);
        }

        deal = deck.interator();
    }

    public boolean hasNextCard() {
        return (deal.hasNext());
    }

    public Card nextCard() {
        return deal.next();
    }
}
