package gui.ptimoGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PanelAnimation extends JPanel {

    private PtimoGUI gui;

    private int juliette_x = 0;
    private int juliette_y = 0;
    private int julietteSize_W = 150;
    private int julietteSize_H = 170;
    private final Image[] juliette_states;
    private int juliette_state;

    private final Image[] sacbleu_states;
    private final Image[] pyralia_states;
    private final Image[] pokrand_states;

    private final Image[] currentPtimo_states;
    private int currentPtimoSize_W;
    private int currentPtimoSize_H;
    private int currentPtimo_x;
    private int currentPtimo_y;
    private int currentPtimoOffset_x;
    private int currentPtimoOffset_y;
    private int currentPtimo_state;

    private Timer timerAnim;

    public PanelAnimation(PtimoGUI ptimoGui) {

        this.gui = ptimoGui;

        timerAnim = new Timer(1000, null);

        setOpaque(false);

        juliette_states = new Image[10];
        juliette_states[0] = gui.getImageIcon(gui.getImagesPath()+"Juliette_idle.gif").getImage();
        juliette_states[1] = gui.getImageIcon(gui.getImagesPath()+"juliette_walk.gif").getImage();
        juliette_states[2] = gui.getImageIcon(gui.getImagesPath()+"juliette_attack.gif").getImage();
        juliette_states[3] = gui.getImageIcon(gui.getImagesPath()+"juliette_throw.gif").getImage();
        juliette_states[4] = gui.getImageIcon(gui.getImagesPath()+"juliette_dance.gif").getImage();
        juliette_states[5] = gui.getImageIcon(gui.getImagesPath()+"juliette_victory.gif").getImage();
        juliette_states[6] = gui.getImageIcon(gui.getImagesPath()+"juliette_dart.gif").getImage();
        juliette_states[7] = gui.getImageIcon(gui.getImagesPath()+"juliette_hit.gif").getImage();
        juliette_states[8] = gui.getImageIcon(gui.getImagesPath()+"juliette_drink.gif").getImage();
        juliette_states[9] = gui.getImageIcon(gui.getImagesPath()+"juliette_ko.gif").getImage();

        currentPtimo_states = new Image[3];

        sacbleu_states = new Image[3];
        sacbleu_states[0] = gui.getImageIcon(gui.getImagesPath()+"sacbleu_idle.gif").getImage();
        sacbleu_states[1] = gui.getImageIcon(gui.getImagesPath()+"sacbleu_attack.gif").getImage();
        sacbleu_states[2] = gui.getImageIcon(gui.getImagesPath()+"sacbleu_attackM.gif").getImage();

        pyralia_states = new Image[3];
        pyralia_states[0] = gui.getImageIcon(gui.getImagesPath()+"pyralia_idle.gif").getImage();
        pyralia_states[1] = gui.getImageIcon(gui.getImagesPath()+"pyralia_attack.gif").getImage();
        pyralia_states[2] = gui.getImageIcon(gui.getImagesPath()+"pyralia_attackM.gif").getImage();

        pokrand_states = new Image[3];
        pokrand_states[0] = gui.getImageIcon(gui.getImagesPath()+"pokrand_idle.gif").getImage();
        pokrand_states[1] = gui.getImageIcon(gui.getImagesPath()+"pokrand_attack.gif").getImage();
        pokrand_states[2] = gui.getImageIcon(gui.getImagesPath()+"pokrand_attackM.gif").getImage();
    }


    /**
     * Change coordiantes of the sprite depending on the size of the current Ptimo
     * @param ptimo The name of the Ptimo. Saclbeu / Pyralia / Pokrand
     */
    public void setCurrentPtimo(String ptimo) {
        Image[] toChange;
        switch (ptimo) {
            case "Pyralia" -> {
                toChange = pyralia_states;
                currentPtimoSize_W = 150;
                currentPtimoSize_H = 200;
                currentPtimoOffset_x = 0;
                currentPtimoOffset_y = 0;
            }
            case "Pokrand" -> {
                toChange = pokrand_states;
                currentPtimoSize_W = 250;
                currentPtimoSize_H = 320;
                currentPtimoOffset_x = -20;
                currentPtimoOffset_y = -120;
            }
            default -> {
                toChange = sacbleu_states;
                currentPtimoSize_W = 100;
                currentPtimoSize_H = 150;
                currentPtimoOffset_x = -20;
                currentPtimoOffset_y = 40;

            }
        }
        currentPtimo_states[0] = toChange[0];
        currentPtimo_states[1] = toChange[1];
        currentPtimo_states[2] = toChange[2];
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(juliette_states[juliette_state], juliette_x, juliette_y, julietteSize_W, julietteSize_H, this);
        g.drawImage(currentPtimo_states[currentPtimo_state], currentPtimo_x, currentPtimo_y, currentPtimoSize_W, currentPtimoSize_H, this);
    }

    /**
     * Do an animation for the given duration
     * Juliette : 0 - Idle / 1 - Walk / 2 - attack / 3 - throw / 4 - dance / 5 - victory / 6 - dart / 7 - hit / 8 - drink / 9 - KO
     * Ptimos : 0 - Idle / 1 - attack / 2 - magic attack
     * @param character "Juliette" or "Ptimo"
     * @param animation the animation state
     * @param duration the duration in milliseconds
     */
    public void doAnimation(String character, int animation, int duration) {
        if (!timerAnim.isRunning()) {
            if (character.equals("Juliette")) {
                if (animation < juliette_states.length) {
                    juliette_states[animation].flush(); // Flush gif so it start from the beginning
                    juliette_state = animation;
                }
            } else {
                if (animation < currentPtimo_states.length) {
                    currentPtimo_states[animation].flush(); // Flush gif so it start from the beginning
                    currentPtimo_state = animation;
                }
            }
            timerAnim.setInitialDelay(duration);
            timerAnim.addActionListener(e -> {
                timerAnim.removeActionListener(timerAnim.getActionListeners()[0]);
                timerAnim.stop();

                if (character.equals("Juliette")) {
                    juliette_state = 0;
                } else {
                    currentPtimo_state = 0;
                }
            });
            timerAnim.start();
        }
    }

    /**
     * Move juliette towards the given direction at given speed
     * @param direction "X" for left and right, "Y" or "" for up and down
     * @param speed how many pixel to the given direction
     */
    public void julietteMove(String direction, int speed){
        if (direction.equals("X")) {
            if ((speed>0 && julietteSize_W>0) || (speed<0 && julietteSize_W<0)) {
                juliette_x += julietteSize_W;
                julietteSize_W = -julietteSize_W;
            }
            if (speed > 1 || speed < -1) { // If 1 or -1 entered, no movement, just change of direction
                if ((juliette_x + speed) < getWidth() && (juliette_x + speed) > 0){
                    juliette_state = 1;
                    juliette_x += speed;
                }
            }
        } else {
            if ((juliette_y + speed) < getHeight() && (juliette_y + speed) > 0){
                juliette_state = 1;
                juliette_y += speed;
            }
        }
    }

    /**
     * Will move Juliette in the given position and direction
     * @param x
     * @param y
     * @param direction 0 -> left / 1 -> right
     */
    public void julietteSetLocation(int x, int y, int direction){
        if ((direction == 1 && julietteSize_W > 0) || (direction == 0 && julietteSize_W < 0)) {
            juliette_x += julietteSize_W;
            julietteSize_W = -julietteSize_W;
        }
        juliette_x = x;
        juliette_y = y;
    }

    /**
     * Controls image switches for animation
     * @param juliette_state 0 - Idle / 1 - Walk / 2 - attack / 3 - throw / 4 - dance / 5 - victory / 6 - dart / 7 - hit / 8 - drink
     */
    public void setJuliette_state(int juliette_state) {
        this.juliette_state = juliette_state;
    }
    public int getJuliette_state() {
        return juliette_state;
    }

    /**
     * Move current Ptimo towards the given direction at given speed
     * @param direction "X" for left and right, "Y" or "" for up and down
     * @param speed how many pixel to the given direction
     */
    public void currentPtimoMove(String direction, int speed) {
        if (direction.equals("X")) {
            if ((speed>0 && currentPtimoSize_W>0) || (speed<0 && currentPtimoSize_W<0)) {
                currentPtimo_x += currentPtimoSize_W;
                currentPtimoSize_W = -currentPtimoSize_W;
            }
            if ((currentPtimo_x + speed) < getWidth() && (currentPtimo_x + speed) > 0){
                currentPtimo_state = 1;
                currentPtimo_x += speed;
            }
        } else {
            if ((currentPtimo_y + speed) < getHeight() && (currentPtimo_y + speed) > 0){
                currentPtimo_state = 1;
                currentPtimo_y += speed;
            }
        }
    }

    /**
     * Will move current Ptimo in the given position and direction
     * @param direction 0 -> left / 1 -> right
     */
    public void ptimoSetLocation(int x, int y, int direction){
        if ((direction == 1 && currentPtimoSize_W > 0) || (direction == 0 && currentPtimoSize_W < 0)) {
            currentPtimo_x += currentPtimoSize_W;
            currentPtimoSize_W = -currentPtimoSize_W;
        }
        currentPtimo_x = x += currentPtimoOffset_x;
        currentPtimo_y = y += currentPtimoOffset_y;
    }
    /**
     * Controls image switches for animation
     * @param currentPtimo_state 1 - Idle / 2 - attack / 3 - Magic attack
     */
    public void setCurrentPtimo_state(int currentPtimo_state) {
        this.currentPtimo_state = currentPtimo_state;
    }
    public int getCurrentPtimo_state() {
        return currentPtimo_state;
    }

}
