package Poker;

import java.util.HashSet;
import java.util.LinkedList;

public class StraightFlush extends Hands {

    private static LinkedList<Card> handList;

    private StraightFlush() {
        name = "StraightFlush";
        value = 8;
        handCards = handList;
    }

    public static Hands success(Card[] cards) {

        if (cards.length == 5) {

            HashSet<String> iterations = new HashSet<String>();
            handList = new LinkedList<Card>();

            for (int i = 0; i < 4; i++) {
                iterations.add(cards[i].getSymbol());
                if (Integer.parseInt(cards[i].getValue()) == Integer.parseInt(cards[i+1].getValue())-1) {
                    if (!handList.contains(cards[i])) { handList.add(cards[i]); }
                    handList.add(cards[i+1]);
                }
            }
            iterations.add(cards[4].getSymbol());

            System.out.println("STFLUSH iterations.size() - "+iterations.size());
            System.out.println("STFLUSH handList.size() - "+handList.size());

            if (iterations.size() == 1 && handList.size() == 5) {
                for (int i = 0; i < 4; i++) { // Second check in the only case all cards are added / Like 3 6 8 9 10
                    if (Integer.parseInt(cards[i].getValue()) != Integer.parseInt(cards[i+1].getValue())-1) {
                        return null;
                    }
                }
                return new StraightFlush();
            }

        }

        return null;
    }
}
