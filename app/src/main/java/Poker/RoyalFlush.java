package Poker;

import java.util.HashSet;
import java.util.LinkedList;

public class RoyalFlush extends Hands {

    private static LinkedList<Card> handList;

    private RoyalFlush() {
        name = "RoyalFlush";
        value = 9;
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

            System.out.println("ROYAL iterations.size() - "+iterations.size());
            System.out.println("ROYAL handList.size() - "+handList.size());

            if (iterations.size() == 1 && handList.size() == 4 && cards[0].getValue().equals("1")) {
                handList.add(cards[0]); //Will be an ace
                return new RoyalFlush();
            }

        }

        return null;
    }
}