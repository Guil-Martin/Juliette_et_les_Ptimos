package Poker;

import java.util.HashSet;
import java.util.LinkedList;

public class Flush extends Hands {

    private static LinkedList<Card> handList;

    private Flush() {
        name = "Flush";
        value = 5;
        handCards = handList;
    }

    public static Hands success(Card[] cards) {

        if (cards.length == 5) {

            HashSet<String> iterations = new HashSet<String>();
            handList = new LinkedList<Card>();

            for (int i = 0; i < 4; i++) {
                iterations.add(cards[i].getSymbol());
                if (cards[i].getSymbol().equals(cards[i+1].getSymbol())) {
                    if (!handList.contains(cards[i])) handList.add(cards[i]);
                    handList.add(cards[i+1]);
                }
            }
            iterations.add(cards[4].getSymbol());

            if (iterations.size() == 1) {
                return new Flush();
            }
        }

        return null;
    }
}
