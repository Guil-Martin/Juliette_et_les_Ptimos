package Poker;

import java.util.HashSet;
import java.util.LinkedList;

public class FullHouse extends Hands {

    private static LinkedList<Card> handList;

    private FullHouse() {
        name = "FullHouse";
        value = 6;
        handCards = handList;
    }

    public static Hands success(Card[] cards) {

        if (cards.length == 5) {

            HashSet<Integer> iterations = new HashSet<Integer>();
            handList = new LinkedList<Card>();

            for (int i = 0; i < 4; i++) {
                iterations.add(Integer.parseInt(cards[i].getValue()));
                if (cards[i].getValue().equals(cards[i+1].getValue())) {
                    if (!handList.contains(cards[i])) handList.add(cards[i]);
                    handList.add(cards[i+1]);
                }
            }
            iterations.add(Integer.parseInt(cards[4].getValue()));

            if (iterations.size() == 2 && handList.size() == 5) {
                return new FullHouse();
            }
        }

        return null;
    }
}
