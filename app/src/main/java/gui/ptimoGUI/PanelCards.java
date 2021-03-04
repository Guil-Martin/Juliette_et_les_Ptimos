package gui.ptimoGUI;

import Poker.Card;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.LinkedList;

public class PanelCards extends JPanel {

    private PtimoGUI gui;

    private final Card[] displayCards = new Card[5];
    private LinkedList<Card> highlightCard;

    public PanelCards(PtimoGUI ptimoGui) {
        gui = ptimoGui;
        this.highlightCard = new LinkedList<>();

        setSize(250, 120);
        setBackground(new Color(15, 95, 4, 255));
        Border lowerBevel = BorderFactory.createLoweredBevelBorder();
        setBorder(lowerBevel);
    }

    /**
     * Will change the cards painted
     * @param cards Set of 5 cards to display
     */
    public void placeCards(Card[] cards, LinkedList<Card> highlightCard) {
        this.highlightCard = highlightCard;
        System.arraycopy(cards, 0, displayCards, 0, displayCards.length);
    }
    public void placeCards(Card[] cards) {
        this.highlightCard.clear(); // in case there is still cards highlithed before
        System.arraycopy(cards, 0, displayCards, 0, displayCards.length);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (int i = 0; i < displayCards.length; i++) {
            int card_x = (i * 50);
            int card_y = 20;
            if (highlightCard.contains(displayCards[i])) card_y -= 20;
            g.drawImage(gui.getImageIcon("/cardImgs/"+displayCards[i].getShortcut()+".png").getImage(), card_x, card_y, 50, 100, null);
        }

    }
}
