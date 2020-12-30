package ch06.apps;

import support.cards.Card;
import support.cards.CradDeck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CradHandGUI {

    static final int HANDSIZE = 5;
    static int handCount = 0;

    static Card nextCard;
    static CradDeck deck = new CradDeck();
    static Card[] hand = new Card[HANDSIZE];

    static JLabel nextCardLabel;
    static JLabel nextCardHolder;
    static JLabel slotLabel;
    static JLabel[] cardLabels = new JLabel[HANDSIZE];
    static JLabel[] slotLabels = new JLabel[HANDSIZE];
    static ButtonGroup slotGroup;
    static JRadioButton[] rbuttons = new JRadioButton[HANDSIZE];

    public static void main(String[] args) {
        deck.shuffle();
        hand[0] = deck.nextCard();
        handCount = 1;
        nextCard = deck.nextCard();

        JFrame frame = new JFrame("CardHandGUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(180 * HANDSIZE, 450));
        mainPanel.setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        northPanel.setBackground(Color.white);
        northPanel.setLayout(new FlowLayout());

        nextCardLabel = new JLabel("下一张牌：");
        nextCardHolder = new JLabel(nextCard.getImage());

        JPanel slotPanel = new JPanel();
        slotPanel.setLayout(new FlowLayout());
        slotLabel = new JLabel("把牌插到哪里？");
        slotPanel.add(slotLabel);

        slotGroup = new ButtonGroup();
        SlotListener listener = new SlotListener();

        for (int i = 0; i < HANDSIZE; i++) {
            rbuttons[i] = new JRadioButton(Integer.toString(i));
            if (i > 1)
                rbuttons[i].setVisible(false);
            rbuttons[i].addActionListener(listener);
            slotGroup.add(rbuttons[i]);
            slotPanel.add(rbuttons[i]);
        }

        northPanel.add(nextCardLabel);
        northPanel.add(nextCardHolder);
        northPanel.add(slotPanel);

        for (int i = 0; i < HANDSIZE; i++)
            slotLabels[i] = new JLabel(Integer.toString(i));

        for (int i = 2; i < HANDSIZE; i++)
            slotLabels[i].setVisible(false);

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.white);
        southPanel.setLayout(new FlowLayout());

        cardLabels[0] = new JLabel(hand[0].getImage());
        for (int i = 1; i < HANDSIZE; i++)
            cardLabels[i] = new JLabel("");

        for (int i = 0; i < HANDSIZE; i++) {
            southPanel.add(slotLabels[i]);
            southPanel.add(cardLabels[i]);
        }
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private static class SlotListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            Object source = event.getSource();
            handCount++;

            int target = 0;

            for (int i = 0; i < HANDSIZE; i++)
                if (source == rbuttons[i])
                    target = i;

            for (int i = handCount - 1; i > target; i--)
                hand[i] = hand[i - 1];

            hand[target] = nextCard;

            for (int i = 0; i < handCount; i++)
                cardLabels[i].setIcon(hand[i].getImage());

            if (handCount != HANDSIZE) {
                slotLabels[handCount].setVisible(true);
                rbuttons[handCount].setVisible(true);
                nextCard = deck.nextCard();
                nextCardHolder.setIcon(nextCard.getImage());
                slotGroup.clearSelection();
            } else {
                for (int i = 0; i < HANDSIZE; i++) {
                    slotLabels[i].setVisible(false);
                    rbuttons[i].setVisible(false);
                }
                slotLabel.setVisible(false);
                nextCardHolder.setVisible(false);
                nextCardLabel.setText("牌已发完！");
            }
        }
    }

}
