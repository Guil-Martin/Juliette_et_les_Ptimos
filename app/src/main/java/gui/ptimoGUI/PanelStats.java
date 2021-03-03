package gui.ptimoGUI;

import game.Game;

import javax.swing.*;
import java.awt.*;

public class PanelStats extends JPanel  {

    private Game game;
    private PtimoGUI gui;

    private final Image juliette_icon;
    private final Image icon_potion;
    private final Image icon_treat;
    private final Image icon_sleeping_dart;

    private JProgressBar julietteHp;
    private JProgressBar ptimoHp;

    public PanelStats(PtimoGUI ptimoGui) {
        this.game = Game.gameInstance();
        this.gui = ptimoGui;

        setLayout(null);
        setSize(400,100);
        setBackground(Color.black);

        juliette_icon = gui.getImageIcon(gui.getImagesPath()+"icon_juliette.png").getImage();
        icon_potion = gui.getImageIcon(gui.getImagesPath()+"icon_potion.png").getImage();
        icon_treat = gui.getImageIcon(gui.getImagesPath()+"icon_treat.png").getImage();
        icon_sleeping_dart = gui.getImageIcon(gui.getImagesPath()+"icon_sleeping_dart.png").getImage();

        julietteHp = new JProgressBar();
        julietteHp.setBackground(Color.black);
        julietteHp.setForeground(Color.red);
        julietteHp.setBounds(100, 0, 300,50);
        add(julietteHp);

        ptimoHp = new JProgressBar();
        ptimoHp.setBackground(Color.black);
        ptimoHp.setForeground(Color.red);

        refresh();

    }

    public void refresh() {
        int percentHp = (int) ((double) game.getJulietteInstance().getHp() / (double) game.getJulietteInstance().getMaxHp() * 100);
        julietteHp.setValue(percentHp);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(juliette_icon, 100, 0, -100, 100, this);

        g.setColor(Color.white);
        g.setFont(new Font("Serif", Font.BOLD, 22));

        g.drawImage(icon_treat, 150, 70, 30, 30, this);
        g.drawString(String.valueOf(game.getJulietteInstance().getTreats()), 182, 92);
        g.drawImage(icon_potion, 220, 70, 30, 30, this);
        g.drawString(String.valueOf(game.getJulietteInstance().getPotions()), 252, 92);
        g.drawImage(icon_sleeping_dart, 290, 70, 30, 30, this);
        g.drawString(String.valueOf(game.getJulietteInstance().getDart()), 322, 92);

        g.setFont(new Font("Serif", Font.BOLD, 40));

        g.drawString(game.getJulietteInstance().getHp() + "/" + game.getJulietteInstance().getMaxHp(), 100, 60);
    }

}