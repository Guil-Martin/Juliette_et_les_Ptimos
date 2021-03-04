package gui.ptimoGUI;

import entities.ptimo.ptimos.Ptimo;

import javax.swing.*;
import java.awt.*;

public class PtimoGUI extends JFrame {

    private static PtimoGUI gui;

    private final Dimension windowDims = new Dimension(1000, 700);

    private final PanelBg panelBg;
    private final PanelAnimation panelAnimation;
    private final PanelActions panelActions;
    private final PanelPtimoballs panelPtimoball;
    private final PanelStats panelStats;
    private final PanelDialogue panelDialogue;
    private final PanelCards panelCards;

    private final JButton btnRestart;

    // 0 - No controls / 1 - combat controls / 2 - Free movement left and right
    private int gameState;

    public static PtimoGUI getInstance() {
        if (gui == null) {
            gui = new PtimoGUI();
        }
        return gui;
    }
    private PtimoGUI() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Juliette et les Ptimos");
        setLayout(null);
        setBounds(0, 0, windowDims.width, windowDims.height);

        Dimension screenDims = getToolkit().getScreenSize();
        setLocation(screenDims.width / 3, screenDims.height / 6);

        btnRestart = new JButton("Recommencer");
        btnRestart.setBounds((getWidth()/2)-100, (getHeight()/2)-50, 200, 100);
        btnRestart.setBackground(new Color(160, 21, 21));
        btnRestart.setForeground(Color.white);
        btnRestart.setVisible(false);
        add(btnRestart);

        // Will display the ptimoball and icons if there is ptimos in it
        panelPtimoball = new PanelPtimoballs(this);
        panelPtimoball.setLocation(400, 0);
        add(panelPtimoball);

        // Hp bars and items
        panelStats = new PanelStats(this);
        panelStats.setLocation(0,0);
        add(panelStats);

        // Handle the hotkeys and choice menu
        panelActions = new PanelActions(this);
        panelActions.setLocation(0,100);
        add(panelActions);

        // Panel that will be used to display the cards of pokrand
        panelCards = new PanelCards(this);
        panelCards.setLocation(500,250);
        panelCards.setVisible(false);
        add(panelCards);

        // Dialogue panel
        panelDialogue = new PanelDialogue(this);
        panelDialogue.setLocation(0,getHeight()-200);
        add(panelDialogue);

        // Handle animations
        panelAnimation = new PanelAnimation(this);
        panelAnimation.setBounds(0,0, getWidth(), getHeight()-200);
        add(panelAnimation);

        // Used to draw a background
        panelBg = new PanelBg(this);
        panelBg.setBounds(0, 0, getWidth(), getHeight());
        panelBg.setBg("house");
        add(panelBg);

        setResizable(false);
        setVisible(true);
    }

    // ===== Getters setters =====
    public Dimension getWindowDims() { return windowDims; }
    public ImageIcon getImageIcon(String path) { return new ImageIcon(getClass().getResource(path)); }
    public String getImagesPath() { return "/ptimoImgs/"; }
    public JButton getBtnRestart() {
        return btnRestart;
    }
    public PanelActions getPanelActions() {
        return panelActions;
    }
    public PanelAnimation getPanelAnimation() {
        return panelAnimation;
    }
    public PanelBg getPanelBg() {
        return panelBg;
    }
    public PanelCards getPanelCards() {
        return panelCards;
    }
    public PanelDialogue getPanelDialogue() {
        return panelDialogue;
    }
    public PanelPtimoballs getPanelPtimoball() {
        return panelPtimoball;
    }
    public PanelStats getPanelStats() {
        return panelStats;
    }
}
