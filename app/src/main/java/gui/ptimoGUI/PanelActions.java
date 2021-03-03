package gui.ptimoGUI;

import game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class PanelActions extends JPanel implements KeyListener, MouseListener {

    private PtimoGUI gui;
    private Game game;

    Dimension iconSize = new Dimension(50,50);

    private ImageIcon icon_check;
    private ImageIcon icon_approach;
    private ImageIcon icon_treat;
    private ImageIcon icon_sleeping_dart;
    private ImageIcon icon_potion;
    private ImageIcon icon_flee;
    private ImageIcon icon_dance;

    Dimension actionSize = new Dimension(150,50);

    private JLabel actionIconCheck;
    private JLabel actionIconApproach;
    private JLabel actionIconPotion;
    private JLabel actionIconTreat;
    private JLabel actionIconSDart;
    private JLabel actionIconFlee;
    private JLabel actionIconDance;

    private JLabel actionCheck;
    private JLabel actionApproach;
    private JLabel actionTreat;
    private JLabel actionDance;
    private JLabel actionPotion;
    private JLabel actionSleepDart;
    private JLabel actionFlee;

    public PanelActions(PtimoGUI ptimoGui) {
        this.gui = ptimoGui;
        this.game = Game.gameInstance();

        // The button will appear when Juliette has 0 hp or Pokrand stuns her
        gui.getBtnRestart().addActionListener(e -> {
            game.restartGame();
        });

        setLayout(new FlowLayout(0,0,0));
        setSize(gui.getWindowDims().width,100);
        setBackground(Color.black);

        icon_check = gui.getImageIcon(gui.getImagesPath()+"icon_check.png");
        icon_approach = gui.getImageIcon(gui.getImagesPath()+"icon_approach.png");
        icon_potion = gui.getImageIcon(gui.getImagesPath()+"icon_potion.png");
        icon_treat = gui.getImageIcon(gui.getImagesPath()+"icon_treat.png");
        icon_sleeping_dart = gui.getImageIcon(gui.getImagesPath()+"icon_sleeping_dart.png");
        icon_flee = gui.getImageIcon(gui.getImagesPath()+"icon_flee.png");
        icon_dance = gui.getImageIcon(gui.getImagesPath()+"icon_dance.png");

        actionIconCheck = new JLabel();
        actionIconCheck.setIcon(icon_check);
        actionIconCheck.setPreferredSize(iconSize);
        actionIconCheck.setBackground(Color.black);

        actionIconApproach = new JLabel();
        actionIconApproach.setIcon(icon_approach);
        actionIconApproach.setPreferredSize(iconSize);

        actionIconPotion = new JLabel();
        actionIconPotion.setIcon(icon_potion);
        actionIconPotion.setPreferredSize(iconSize);

        actionIconTreat = new JLabel();
        actionIconTreat.setIcon(icon_treat);
        actionIconTreat.setPreferredSize(iconSize);

        actionIconSDart = new JLabel();
        actionIconSDart.setIcon(icon_sleeping_dart);
        actionIconSDart.setPreferredSize(iconSize);

        actionIconFlee = new JLabel();
        actionIconFlee.setIcon(icon_flee);
        actionIconFlee.setPreferredSize(iconSize);

        actionIconDance = new JLabel();
        actionIconDance.setIcon(icon_dance);
        actionIconDance.setPreferredSize(iconSize);

        actionCheck = new JLabel("1 Observer", SwingConstants.LEFT);
        actionCheck.setPreferredSize(actionSize);
        actionCheck.setForeground(Color.white);
        actionCheck.addMouseListener(this);

        actionApproach = new JLabel("2 Approcher", SwingConstants.LEFT);
        actionApproach.setPreferredSize(actionSize);
        actionApproach.setForeground(Color.white);
        actionApproach.addMouseListener(this);

        actionTreat= new JLabel("3 Lancer une friandise", SwingConstants.LEFT);
        actionTreat.setPreferredSize(actionSize);
        actionTreat.setForeground(Color.white);
        actionTreat.addMouseListener(this);

        actionDance = new JLabel("4 Danser", SwingConstants.LEFT);
        actionDance.setPreferredSize(actionSize);
        actionDance.setForeground(Color.white);
        actionDance.addMouseListener(this);

        actionPotion = new JLabel("5 Boire une potion", SwingConstants.LEFT);
        actionPotion.setPreferredSize(actionSize);
        actionPotion.setForeground(Color.white);
        actionPotion.addMouseListener(this);

        actionSleepDart = new JLabel("6 Fléchette endormissante", SwingConstants.LEFT);
        actionSleepDart.setPreferredSize(actionSize);
        actionSleepDart.setForeground(Color.white);
        actionSleepDart.addMouseListener(this);

        actionFlee = new JLabel("7 Partir", SwingConstants.LEFT);
        actionFlee.setPreferredSize(actionSize);
        actionFlee.setForeground(Color.white);
        actionFlee.addMouseListener(this);

        add(actionIconCheck);
        add(actionCheck);

        add(actionIconApproach);
        add(actionApproach);

        add(actionIconTreat);
        add(actionTreat);

        add(actionIconDance);
        add(actionDance);

        add(actionIconPotion);
        add(actionPotion);

        add(actionIconSDart);
        add(actionSleepDart);

        add(actionIconFlee);
        add(actionFlee);

        // check Ptimo
        // approach
        // throw treat
        // Dance
        // Take potion
        // sleeping dart
        // flee

        addKeyListener(this);
        setFocusable(true);
    }

    /**
     * To refacto by adding all of this in a panel
     * @param visible
     */
    public void actionsVisible(boolean visible) {

        actionIconCheck.setVisible(visible);
        actionCheck.setVisible(visible);

        actionIconApproach.setVisible(visible);
        actionApproach.setVisible(visible);

        actionIconTreat.setVisible(visible);
        actionTreat.setVisible(visible);

        actionIconDance.setVisible(visible);
        actionDance.setVisible(visible);

        actionIconPotion.setVisible(visible);
        actionPotion.setVisible(visible);

        actionIconSDart.setVisible(visible);
        actionSleepDart.setVisible(visible);

        actionIconFlee.setVisible(visible);
        actionFlee.setVisible(visible);

        setOpaque(visible);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (game.getGameState()) {
            case 1 -> {
                // Combat state
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_1 -> game.action(1);
                    case KeyEvent.VK_2 -> game.action(2);
                    case KeyEvent.VK_3 -> game.action(3);
                    case KeyEvent.VK_4 -> game.action(4);
                    case KeyEvent.VK_5 -> game.action(5);
                    case KeyEvent.VK_6 -> game.action(6);
                    case KeyEvent.VK_7 -> game.action(7);
                }
            }
            case 2 -> {
                // Free movement state
                switch (e.getKeyCode()) {
                    /*
                    case KeyEvent.VK_LEFT -> game.action(1);
                    case KeyEvent.VK_RIGHT -> game.action(2);
                    //case KeyEvent.VK_UP -> julietteMove("Y",-10);
                    //case KeyEvent.VK_DOWN -> julietteMove("Y",10);
                     */
                }
            }
            default -> {

            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {    }
    @Override
    public void keyReleased(KeyEvent e) {
        switch (game.getGameState()) {
            case 1 -> {
                // Combat state
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_1 -> {}
                    case KeyEvent.VK_2 -> {}
                    case KeyEvent.VK_3 -> {}
                    case KeyEvent.VK_4 -> {}
                    case KeyEvent.VK_5 -> {}
                    case KeyEvent.VK_6 -> {}
                    case KeyEvent.VK_7 -> {}
                }

            }
            case 2 -> {
                // Free movement state
                switch (e.getKeyCode()) {
                    /*
                    case KeyEvent.VK_LEFT -> game.actionRelease(1);
                    case KeyEvent.VK_RIGHT -> game.actionRelease(2);
                    //case KeyEvent.VK_UP -> julietteMove("Y",-10);
                    //case KeyEvent.VK_DOWN -> julietteMove("Y",10);
                     */
                }
            }
            default -> {

            }
        }


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel label = (JLabel) e.getSource();
        switch (game.getGameState()) {
            case 1 -> {
                // Combat state
                switch (Integer.parseInt(String.valueOf(label.getText().charAt(0)))) {
                    case 1 -> game.action(1);
                    case 2 -> game.action(2);
                    case 3 -> game.action(3);
                    case 4 -> game.action(4);
                    case 5 -> game.action(5);
                    case 6 -> game.action(6);
                    case 7 -> game.action(7);
                }

            }
            default -> {

            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JLabel label = (JLabel) e.getSource();
        label.setForeground(Color.blue);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JLabel label = (JLabel) e.getSource();
        label.setForeground(Color.white);
    }

    @Override
    public void mousePressed(MouseEvent e) {    }

    @Override
    public void mouseReleased(MouseEvent e) {    }

}
