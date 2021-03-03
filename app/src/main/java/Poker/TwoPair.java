package Poker;

import java.util.HashSet;
import java.util.LinkedList;

public class TwoPair extends Hands {

    private static LinkedList<Card> handList;

    private TwoPair() {
        name = "TwoPair";
        value = 2;
        handCards = handList;
    }

    public static Hands success(Card[] cards) {

        if (cards.length == 5) {

            HashSet<Integer> iterations = new HashSet<Integer>();
            handList = new LinkedList<Card>();

            for (int i = 0; i < 4; i++) {
                iterations.add(Integer.parseInt(cards[i].getValue()));

                if (cards[i].getValue().equals(cards[i+1].getValue())) {
                    System.out.println(cards[i] +" Added to two pair");

                    if (!handList.contains(cards[i])) handList.add(cards[i]);
                    handList.add(cards[i+1]);
                }
            }
            iterations.add(Integer.parseInt(cards[4].getValue()));

            System.out.println("THREE iterations.size() - "+iterations.size());
            System.out.println("THREE handList.size() - "+handList.size());

            if (iterations.size() == 3) {
                return new TwoPair();
            }

        }

        return null;
    }


}
