package gui.ptimoGUI;

import entities.ptimo.ptimos.Ptimo;
import game.Game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class PanelPtimoballs extends JPanel {

    private Game game;
    private PtimoGUI gui;

    private Image iconSacbleu;
    private Image iconPyralia;
    private Image iconPokerand;
    private Image iconPtimo;

    public PanelPtimoballs(PtimoGUI ptimoGui) {

        this.game = Game.gameInstance();
        this.gui = ptimoGui;

        setLayout(null);
        setSize(600, 100);
        setBackground(new Color(200, 150, 80, 255));

        Border lowerBevel = BorderFactory.createLoweredBevelBorder();
        setBorder(lowerBevel);

        iconPtimo = gui.getImageIcon(gui.getImagesPath()+"icon_ptimo.png").getImage();
        iconSacbleu = gui.getImageIcon(gui.getImagesPath()+"icon_sacbleu.png").getImage();
        iconPyralia = gui.getImageIcon(gui.getImagesPath()+"icon_pyralia.png").getImage();
        iconPokerand = gui.getImageIcon(gui.getImagesPath()+"icon_pokrand.png").getImage();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        ArrayList<Ptimo> ptimos = game.getJulietteInstance().getPtimoball();

        for (int j = 0; j < 10; j++) {
            g.drawImage(iconPtimo, j*60, 0, 60, 40, this);
        }

        for (int i = 0; i < ptimos.size(); i++) {
                switch (ptimos.get(i).getName()) {
                case "Pyralia" -> {
                    g.drawImage(iconPyralia, i*60, 40, 60, 60, this);
                }
                case "Pokrand" -> {
                    g.drawImage(iconPokerand, i*60, 40, 60, 60, this);
                }
                default -> {
                    g.drawImage(iconSacbleu, i*60, 40, 60, 60, this);
                }
            }
        }
    }

}
