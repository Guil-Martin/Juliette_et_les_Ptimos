package Poker;

import java.util.Arrays;

public class BestHand {

    private BestHand() {}

    public static Hands besthand(Card[] cards) {

        Arrays.sort(cards);

        Hands bestHand = null;

        bestHand = RoyalFlush.success(cards);

        if (bestHand == null) bestHand = StraightFlush.success(cards);

        if (bestHand == null) bestHand = FourOfAKind.success(cards);

        if (bestHand == null) bestHand = FullHouse.success(cards);

        if (bestHand == null) bestHand = Flush.success(cards);

        if (bestHand == null) bestHand = Straight.success(cards);

        if (bestHand == null) bestHand = ThreeOfAKind.success(cards);

        if (bestHand == null) bestHand = TwoPair.success(cards);

        if (bestHand == null) bestHand = Pair.success(cards);

        return bestHand;
    }



}
