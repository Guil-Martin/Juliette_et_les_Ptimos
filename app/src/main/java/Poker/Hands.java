package Poker;

import java.util.LinkedList;

public abstract class Hands {

    protected String name;
    protected int value;

    // List that will be used to highlight the hand in the PanelCards class
    protected LinkedList<Card> handCards;

    public Hands() {}

    // ===== Getters setters =====
    // Will return the cards concerned by the hand
    public LinkedList<Card> getHand() {
        return handCards;
    }

    public String getHandName() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        value = value;
    }

}
