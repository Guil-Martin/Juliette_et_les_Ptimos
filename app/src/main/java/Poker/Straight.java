package Poker;

import java.util.HashSet;
import java.util.LinkedList;

public class Straight extends Hands {

    private static LinkedList<Card> handList;

    private Straight() {
        name = "Straight";
        value = 4;
        handCards = handList;
    }

    public static Hands success(Card[] cards) {

        if (cards.length == 5) {

            HashSet<Integer> iterations = new HashSet<Integer>();
            handList = new LinkedList<Card>();

            for (int i = 0; i < 4; i++) {
                iterations.add(Integer.parseInt(cards[i].getValue()));
                if (Integer.parseInt(cards[i].getValue()) == Integer.parseInt(cards[i+1].getValue())-1) {
                    if (!handList.contains(cards[i])) handList.add(cards[i]);
                    handList.add(cards[i+1]);
                }
            }
            iterations.add(Integer.parseInt(cards[4].getValue()));

            System.out.println("STRAIGHT iterations.size() - "+iterations.size());
            System.out.println("STRAIGHT handList.size() - "+handList.size());

            if (iterations.size() == 5) {
                if (handList.size() == 5) {
                    for (int i = 0; i < 4; i++) { // Second check in the only case all cards are added / Like 3 6 8 9 10
                          if (Integer.parseInt(cards[i].getValue()) != Integer.parseInt(cards[i+1].getValue())-1) {
                            return null;
                        }
                    }
                    return new Straight();
                } else if (handList.size() == 4 && cards[0].getValue().equals("1") && cards[4].getValue().equals("13")) {
                    handList.add(cards[0]); // Straight with ace to the end, add it in the cards result
                    return new Straight();
                }
            }

        }

        return null;
    }

}
