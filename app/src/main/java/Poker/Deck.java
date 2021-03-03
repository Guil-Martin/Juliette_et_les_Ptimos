package Poker;

import com.google.common.collect.Multimap;

import java.util.*;

public class Deck {

    private final ArrayList<Card> deck = new ArrayList<Card>();
    private final ArrayList<Card> strongestHand = new ArrayList<Card>();
    Random randGen = new Random();

    private static Deck deckInstance;

    private Deck() {
        // Create 52 cards
        createCards("d");
        createCards("h");
        createCards("c");
        createCards("s");

        /*
        for (Card c : deck) {
            System.out.println(c);
        }
        */
        System.out.println("Deck size - "+deck.size());
    }

    public static Deck getInstance() {
        if (deckInstance == null) {
            deckInstance = new Deck();
        }
        return deckInstance;
    }

    /**
     *
     * @param symbol
     */
    private void createCards(String symbol) {
        for (int i=0; i<10; i++) {
            Card toAdd = new Card(symbol, String.valueOf(i+1));
            deck.add(toAdd);
        }
        deck.add(new Card(symbol, "j"));
        deck.add(new Card(symbol, "q"));
        deck.add(new Card(symbol, "k"));
    }

    /**
     * Get a specific card
     * first the symbol (d c h s) followed by the value (1-10 j q k)
     * @param name
     * @return
     */
    public Card getCard(String name) {
        String cardName = "";

        String symbol = name.substring(0,1);
        symbol = symbol.trim();
        String value = name.substring(1);
        value = value.trim();

        switch (value) {
            case "j" -> cardName += "Jack";
            case "q" -> cardName += "Queen";
            case "k" -> cardName += "King";
            case "1" -> cardName += "Ace";
            default -> cardName += value;
        }

        switch (symbol) {
            case "d" -> cardName += " of Diamond";
            case "c" -> cardName += " of Clubs";
            case "h" -> cardName += " of Heart";
            case "s" -> cardName += " of Spade";
            default -> cardName += "";
        }

        for (Card c : deck) {
            if (c.getName().equals(cardName)) {
                removeCardFromDeck(c);
                return c;
            }
        }

        return getRandomCard();
    }

    public void addCardToDeck(Card cardToAdd) {
        deck.add(cardToAdd);
    }
    public void addCardToDeck(ArrayList<Card> cardsToAdd) {
        deck.addAll(cardsToAdd);
    }
    public void addCardToDeck(Card[] cardsToAdd) {
        for (int i = 0; i < cardsToAdd.length; i++) {
            deck.add(cardsToAdd[i]);
        }
    }

    public void removeCardFromDeck(Card cardToRemove) {
        deck.remove(cardToRemove);
    }

    public Card getRandomCard() {
        Card cardToReturn = deck.get(randGen.nextInt(deck.size()));
        removeCardFromDeck(cardToReturn);

        return cardToReturn;
    }

    private ArrayList<Card> testCardSymbol(Card[] cards) {
        ArrayList<Card> match = new ArrayList<Card>();
        for (int i=1; i<cards.length; i++) {

            if (cards[0].getSymbol().equals(cards[i].getSymbol())) {
                match.add(cards[i]);
            }

        }

        return match;
    }

    private ArrayList<Card> testCardValues(Card[] cards, int index) {
        ArrayList<Card> match = new ArrayList<Card>();

        for (int i=index+1; i<cards.length; i++) {
            if (cards[index].getValue().equals(cards[i].getValue())) {
                match.add(cards[i]);
                strongestHand.add(cards[i]);
            }
        }

        return match;
    }

    public boolean isStraight(Card[] cards) {
        int match = 0;
        for (int i = 0; i < cards.length-1; i++) {
            if (Integer.parseInt(cards[i].getValue()) == Integer.parseInt(cards[i+1].getValue())-1) match++;
        }
        // Check if there is flush or royal flush by checking if the first card is an ace
        return (match == 4) || (match == 3) && cards[0].getValue().equals("1");
    }
    public boolean isStraightAce(Card[] cards) {
        return (cards[1].getValue().equals("10"));
    }

    public ArrayList<Card> getStrongestHand() { return strongestHand; }

    public ArrayList<Card> getDeck() {
        return deck;
    }

}
