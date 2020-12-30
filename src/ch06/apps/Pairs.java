package ch06.apps;

import ch06.lists.ArrayBoundedList;
import ch06.lists.ListInterface;
import support.cards.Card;
import support.cards.CradDeck;

public class Pairs {
    public static void main(String[] args) {
        final int HANDSIZE = 5;
        final int NUMHANDS = 1000000;
        int numPairs = 0;
        boolean isPair;
        float probability;

        Card card;
        CradDeck deck = new CradDeck();

        ListInterface<Card> hand;

        for (int i = 0; i < NUMHANDS; i++) {
            deck.shuffle();
            hand = new ArrayBoundedList<Card>(HANDSIZE);
            isPair = false;
            for (int j = 0; j < HANDSIZE; j++) {
                card = deck.nextCard();
                if (hand.contains(card))
                    isPair = true;
                hand.add(card);
            }
            if (isPair)
                numPairs++;
        }

        probability = numPairs / (float) NUMHANDS;

        System.out.println();
        System.out.println(" 在 " + NUMHANDS + "次抽牌中，");
        System.out.println(" 抽到对子的次数是 " + numPairs + "，");
        System.out.println(" 抽到对子的概率为 " + probability + "。");
    }
}
