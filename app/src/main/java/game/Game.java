package game;

import Poker.BestHand;
import Poker.Card;
import Poker.Deck;
import Poker.Hands;
import entities.ptimo.Juliette;
import entities.ptimo.ptimos.Ptimo;
import gui.ptimoGUI.PtimoGUI;

import javax.swing.*;
import java.util.Random;

public class Game {

    private static Game game;
    private Deck deck;

    private PtimoGUI gui;
    // 0 - No controls / 1 - combat controls / 2 - Free movement
    private int gameState;

    private final RandomEncounter randomEncounter;
    private final Juliette juliette;
    private final int jInitial_x = 650;
    private final int jInitial_y = 270;
    private Ptimo currentPtimo;

    Random randomGen = new Random();
    private final Timer performAction;
    private final Timer delayTurn;

    private Game() {
        this.juliette = Juliette.getJulietteInstance();
        randomEncounter = RandomEncounter.getEvents();
        performAction = new Timer(1000, null);
        delayTurn = new Timer(3000, null);
    }

    public static Game gameInstance() {
        if (game == null) {
            game = new Game();
        }
        return game;
    }

    /**
     * Restart the game, resets juliette's stuff
     * Called on PanelActions class using an action listener of a button on PtimoGUI class
     */
    public void restartGame() {
        gui.getBtnRestart().setVisible(false);
        gui.getPanelBg().setBg("house");
        juliette.setHp(100);
        juliette.setTreats(30);
        juliette.setPotions(5);
        juliette.setDart(1);
        juliette.getPtimoball().clear();
        gui.getPanelStats().refresh();
        beginGame();
    }

    /**
     * Start the game, place Juliette in her hom and play a little cutscene before starting the first combat
     */
    public void beginGame() {

        // Initiate interface, unique instance
        gui = PtimoGUI.getInstance();

        gui.getPanelActions().actionsVisible(false);
        gameState = 2;

        gui.getPanelAnimation().julietteSetLocation(jInitial_x, jInitial_y,0);
        gui.getPanelAnimation().setJuliette_state(0);

        gui.getPanelDialogue().changeDialogue("Il est temps pour "+juliette.getName()+" de partir à la chasse au Ptimos !");

        performAction.setInitialDelay(6000);
        performAction.addActionListener(e -> {
            performAction.removeActionListener(performAction.getActionListeners()[0]);
            performAction.stop();

            gui.getPanelBg().setBg("forest");

            beginCombat();

        });
        performAction.start(); // Define new actionlistener before starting the timer

    }

    /**
     * Setup the combat phase and make actions from the player available
     */
    private void beginCombat() {

        gui.getPanelAnimation().setJuliette_state(0);

        // Set initial distance between juliette and the Ptimo
        int distance = randomGen.nextInt(7) + 8;
        juliette.setDistance(distance);

        System.out.println("distance - " + distance);

        // Place juliette depending of the initial distance
        gui.getPanelAnimation().julietteSetLocation(Math.max(35, (700-(distance*35))),jInitial_y,1);

        // Create next ptimo encounter
        currentPtimo = randomEncounter.getRandomEncounter(juliette);

        // Set up graphic ptimo, get it in place
        gui.getPanelAnimation().setCurrentPtimo(currentPtimo.getName());
        gui.getPanelAnimation().ptimoSetLocation(700,250,0);

        System.out.format("juliette - %s\n", juliette);
        System.out.format("currentPtimo - %s\n", currentPtimo);

        gui.getPanelDialogue().changeDialogue("Un "+currentPtimo.getName()+" sauvage apparaît !");
        gui.getPanelActions().actionsVisible(true);
        gameState = 1;
    }

    /**
     * Set state out of combat, check if the conditions of winning are completed, if so end the game, else
     * begin another fight
     */
    private void outOfCombat() {
        if (juliette.getPtimoball().size()>5 && juliette.hasPtimo("Sacbleu") && juliette.hasPtimo("Pyralia") && juliette.hasPtimo("Pokrand")) {
            // VICTORY
            gui.getPanelAnimation().julietteSetLocation(jInitial_x,jInitial_y,0);
            gui.getPanelAnimation().doAnimation("Juliette", 5, 5000);
            gui.getPanelDialogue().changeDialogue(juliette.getName()+" a réussi à capturer un Ptimo de chaque espèce ! Bravo !");
            gui.getBtnRestart().setVisible(true);

        } else {

            beginCombat();

        }
    }

    /**
     * This method will be triggered by hotkeys events in PanelActions
     * @param action The chosen action
     */
    public void action(int action) {
        switch (gameState) {
            case 1 -> {
                // Combat
                switch (action) {
                    case 1 -> {
                        // check Ptimo
                        gameState = 0;
                        System.out.println("action check");
                        gui.getPanelDialogue().changeDialogue(juliette.checkPtimo(currentPtimo));
                        gameState = 1;
                    }
                    case 2 -> {
                        gameState = 0;
                        if (!performAction.isRunning()) {
                            // approach

                            // Distance between 3 and 8 meters
                            int travelDistance = randomGen.nextInt(5) + 3;
                            int remainingDistance = juliette.approach(travelDistance);
                            if (remainingDistance < 1) {
                                travelDistance = travelDistance + juliette.getDistance(); // Make sure the travelDistance isn't too high if distance if negative
                            }

                            System.out.println("travelDistance - "+travelDistance);
                            System.out.println("juliette.getDistance() - "+juliette.getDistance());

                            // Juliette moves with travelDistance if needed
                            gui.getPanelAnimation().julietteMove("X", travelDistance*35);
                            gui.getPanelAnimation().setJuliette_state(0);

                            if (remainingDistance < 1) {
                                performAction.setInitialDelay(1000);
                                gui.getPanelAnimation().doAnimation("Juliette", 2, 1000);
                                performAction.addActionListener(e -> {
                                    performAction.removeActionListener(performAction.getActionListeners()[0]);
                                    performAction.stop();

                                    gui.getPanelAnimation().ptimoSetLocation(1300,0,1);
                                    juliette.addPtimo(currentPtimo);
                                    gui.getPanelDialogue().changeDialogue(juliette.getName()+" a capturé le Ptimo "+currentPtimo.getName()+" !");

                                    transition(1, 1);
                                });
                                performAction.start(); // Define new actionlistener before starting the timer
                            } else {
                                gui.getPanelDialogue().changeDialogue(juliette.getName()+" s'avance vers "+currentPtimo.getName()+" de "+travelDistance+" mètres.");
                                transition(0);
                            }

                        }
                    }
                    case 3 -> {
                        // throw treat
                        gameState = 0;
                        if (!performAction.isRunning()) {

                            if (juliette.getTreats() > 0) {
                                performAction.setInitialDelay(1000);
                                gui.getPanelAnimation().doAnimation("Juliette", 3, 1000);

                                performAction.addActionListener(e -> {
                                    performAction.removeActionListener(performAction.getActionListeners()[0]);
                                    performAction.stop();

                                    int malusRange = (int) ((((juliette.getDistance() / 15.0) * 100.0) * 60.0) / 100.0);
                                    int rand = (randomGen.nextInt((61 - malusRange))+20) ;
                                    juliette.throwTreat();

                                    // 30% chance of missing
                                    if (rand > 30) {
                                        gui.getPanelDialogue().changeDialogue(juliette.getName()+" lance une friandise au Ptimo "+currentPtimo.getName()+", cela semble l'avoir apaisé.");
                                        currentPtimo.addStress(-(randomGen.nextInt(16)+15));
                                        System.out.println("Throw treat success - "+currentPtimo);
                                    } else {
                                        gui.getPanelDialogue().changeDialogue(juliette.getName()+" lance une friandise au Ptimo "+currentPtimo.getName()+" mais elle rate ! Le ptimo semble un peu plus méfiant et féroce !");
                                        currentPtimo.addStress(randomGen.nextInt(6)+5);
                                        currentPtimo.addDominance(randomGen.nextInt(6)+5);
                                        System.out.println("Throw treat fail - "+currentPtimo);
                                    }

                                    transition(0);
                                });
                                performAction.start(); // Define new actionlistener before starting the timer
                            } else {
                                gui.getPanelDialogue().changeDialogue(juliette.getName()+" n'a plus de friandises !");
                            }

                        }


                    }
                    case 4 -> {
                        // Dance
                        gameState = 0;

                        performAction.setInitialDelay(1000);
                        gui.getPanelAnimation().doAnimation("Juliette", 4, 1000);
                        performAction.addActionListener(e -> {
                            performAction.removeActionListener(performAction.getActionListeners()[0]);
                            performAction.stop();

                            currentPtimo.addDominance(randomGen.nextInt(15)+7);
                            gui.getPanelDialogue().changeDialogue(juliette.getName()+" effectue une danse impressionnante, "+currentPtimo.getName()+" semble moins féroce !");

                            transition(0);

                        });
                        performAction.start();
                    }
                    case 5 -> {
                        // Take potion
                        gameState = 0;

                        performAction.setInitialDelay(1000);
                        gui.getPanelAnimation().doAnimation("Juliette", 8, 1000);
                        performAction.addActionListener(e -> {
                            performAction.removeActionListener(performAction.getActionListeners()[0]);
                            performAction.stop();

                            int valueHealed = juliette.drinkPotion();
                            if (valueHealed > 0){
                                gui.getPanelDialogue().changeDialogue(juliette.getName()+" boit une potion de soin ! Elle récupère "+valueHealed+" points de vie !");
                                gui.getPanelStats().refresh();
                            } else {
                                gui.getPanelDialogue().changeDialogue(juliette.getName()+" cherche une potion dans son sac mais n'en trouve pas !");
                            }

                            transition(0);

                        });
                        performAction.start();

                    }
                    case 6 -> {
                        // sleeping dart
                        gameState = 0;

                        performAction.setInitialDelay(1000);
                        gui.getPanelAnimation().doAnimation("Juliette", 6, 1000);
                        performAction.addActionListener(e -> {
                            performAction.removeActionListener(performAction.getActionListeners()[0]);
                            performAction.stop();

                            if (juliette.fireDart()) {
                                if ((randomGen.nextInt(100)+1) < 50) {
                                    gui.getPanelDialogue().changeDialogue(juliette.getName()+" tire une fléchette endormante sur le Ptimo "+currentPtimo.getName()+" ! Elle le capture !");

                                    // make Ptimo disappear and add it to the ptimoball list
                                    gui.getPanelAnimation().ptimoSetLocation(1300,0,1);
                                    juliette.addPtimo(currentPtimo);

                                    transition(1, 1);
                                } else {
                                    gui.getPanelDialogue().changeDialogue(juliette.getName()+" rate sa cible !");
                                    transition(0);
                                }
                            } else {
                                gui.getPanelDialogue().changeDialogue(juliette.getName()+" n'a plus de fléchette endormante !");
                                transition(0);
                            }

                        });
                        performAction.start();
                    }
                    case 7 -> {
                        gameState = 0;

                        gui.getPanelDialogue().changeDialogue(juliette.getName()+" tente de s'en aller !");

                        performAction.setInitialDelay(2000);
                        gui.getPanelAnimation().setJuliette_state(1);
                        gui.getPanelAnimation().julietteMove("X",-1);
                        performAction.addActionListener(e -> {
                            performAction.removeActionListener(performAction.getActionListeners()[0]);
                            performAction.stop();

                            // Chance for the ptimo to attack once when Juliette runs away
                            if ((juliette.getDistance() < 10 && currentPtimo.getDominance() > 60) || currentPtimo.getDominance() > 75)  {
                                int damage = currentPtimo.attack(juliette);
                                gui.getPanelDialogue().changeDialogue(juliette.getName()+" réussi à s'en aller mais le Ptimo l'attaque une dernière fois dans le dos pour "+damage+" points de dégâts !");
                                gui.getPanelAnimation().doAnimation("Juliette",7,1000);
                            } else {
                                gui.getPanelDialogue().changeDialogue(juliette.getName()+" réussi à s'en aller sans encombres.");
                            }

                            gui.getPanelAnimation().ptimoSetLocation(1300,0,1);
                            gui.getPanelAnimation().julietteMove("X",1);
                            gui.getPanelAnimation().setJuliette_state(0);
                            transition(1, 1);

                        });
                        performAction.start();

                        transition(1);
                    }
                    default -> {}
                }
            }
            case 2 -> {
                // Free movement left and right
                switch (action) {
                    case 1 -> gui.getPanelAnimation().julietteMove("X",-10);
                    case 2 -> gui.getPanelAnimation().julietteMove("X",10);
                    default -> {}
                }
            }
            default -> {

            }
        }
    }

    /**
     * Mostly used to switch back the animations states when key are released
     * @param action The chosen action
     */
    public void actionRelease(int action) {
        switch (gameState) {
            case 1 -> {}
            case 2 -> {
                switch (action) {
                    case 1 -> gui.getPanelAnimation().setJuliette_state(0);
                    case 2 -> gui.getPanelAnimation().setJuliette_state(0);
                    default -> gui.getPanelAnimation().setJuliette_state(0);
                }
            }
            default -> {

            }
        }

    }

    /**
     * Handle delay before next turn and action
     * @param turn 0 - Ptimo turn / 1 - Juliette turn
     */
    public void transition(int turn) {
        delayTurn.setInitialDelay(2000);
        delayTurn.addActionListener(e -> {
            delayTurn.removeActionListener(delayTurn.getActionListeners()[0]);
            delayTurn.stop();

            if (turn == 1) {
                if (juliette.getHp() < 1) {
                    gui.getPanelAnimation().setJuliette_state(9); // KO animation
                    gui.getPanelAnimation().julietteSetLocation(jInitial_x,jInitial_y,0);
                    gui.getPanelDialogue().changeDialogue(juliette.getName()+"Juliette a perdu connaissance, c'est la défaite !");
                    gui.getPanelAnimation().ptimoSetLocation(1300,0,1);
                    gui.getBtnRestart().setVisible(true);
                } else {
                    gameState = 1;
                }
            }
            else ptimoTurn();

        });
        delayTurn.start();
    }
    public void transition(int turn, int outOfCOmbat) {
        gui.getPanelActions().actionsVisible(false);
        gameState = 2;
        delayTurn.setInitialDelay(4000);
        delayTurn.addActionListener(e -> {
            delayTurn.removeActionListener(delayTurn.getActionListeners()[0]);
            delayTurn.stop();

            outOfCombat();
        });
        delayTurn.start();
    }

    /**
     * Turn of the Ptimo in combat
     * Use the PtimoAI class to return an int that will represent the decision of the Ptimo
     * and then operate the return in the combat
     */
    public void ptimoTurn() {

        // get int that represents a dicision of the ptimo
        int choice = PtimoAI.getInstance().decision(currentPtimo);

        if (choice == 1) {
            // Attack
            attack();
        } else if (choice == 2) {
            // Magic attack
            if (currentPtimo.getName().equals("Pokrand")) {

                deck = Deck.getInstance();

                // Draw 5 cards and test the hand, return a value as an object depending
                Card[] hand = new Card[5];
                for (int i = 0; i < hand.length; i++) {
                    hand[i] = deck.getRandomCard();
                }

                /*
                hand[0] = deck.getCard("c6");
                hand[1] = deck.getCard("d6");
                hand[2] = deck.getCard("dq");
                hand[3] = deck.getCard("cq");
                hand[4] = deck.getCard("sq");
                                 */

                deck.addCardToDeck(hand);

                Hands bestHand = BestHand.besthand(hand);

                if (bestHand != null) {
                    gui.getPanelCards().placeCards(hand, bestHand.getHand()); // Add cards into the method BEFORE displaying the panel, else big errors
                }
                else gui.getPanelCards().placeCards(hand);

                gui.getPanelCards().setVisible(true);
                gui.getPanelDialogue().changeDialogue("Le Ptimo "+currentPtimo.getName()+" sort une main de Poker !");

                performAction.setInitialDelay(3000);
                gui.getPanelAnimation().doAnimation("Ptimo", 2, 1000);
                performAction.addActionListener(ej -> {
                    performAction.removeActionListener(performAction.getActionListeners()[0]);
                    performAction.stop();

                    int bestHandValue = 0;
                    gui.getPanelCards().setVisible(false);
                    if (bestHand != null) {
                        System.out.println(bestHand.getName());
                        bestHandValue = bestHand.getValue();
                        gui.getPanelDialogue().changeDialogue("Le Ptimo "+currentPtimo.getName()+" obtient un "+bestHand.getHandName());
                        System.out.println("Pokrand obtient un "+bestHand.getHandName());
                    } else {
                        System.out.println("No hand");
                    }
                    System.out.println("bestHandValue - "+bestHandValue);

                    if (bestHandValue >= 6) { // Fullhouse, FourOfAKind, Straight flush
                        gui.getPanelAnimation().ptimoSetLocation(1300,0,1);
                        gui.getPanelDialogue().changeDialogue("Le Ptimo "+currentPtimo.getName()+" assome "+juliette.getName()+" et libère tous ses Ptimos capturés !");
                        juliette.getPtimoball().clear();
                        gui.getPanelAnimation().setJuliette_state(9);
                        transition(1, 1);
                    } else if (bestHandValue >= 3) { // ThreeofAKind, Straight, Flush
                        gui.getPanelDialogue().changeDialogue("Le Ptimo "+currentPtimo.getName()+" a "+(bestHand.getHandName().equals("ThreeOfAKind") ? "un brelan" : (bestHand.getHandName().equals("Straight") ? "une suite" : "une couleur")+ ", il attaque Juliette une dernière fois avant de fuir !"));

                        attack();
                        flee();
                    } else if (bestHandValue >= 1){
                        //Si le combo est supérieur ou égale à une paire (paire ou double paire), le Pokerand fait une attaque magique commune.
                        gui.getPanelDialogue().changeDialogue("Le Ptimo "+currentPtimo.getName()+" a "+(bestHand.getHandName().equals("TwoPair") ? "deux paires" : "une paire")+ ", il inflige à Juliette une puissante attaque !");
                        magicAttack();
                    } else {
                        currentPtimo.addDominance(-10);
                        gui.getPanelDialogue().changeDialogue(currentPtimo.getName()+" n'a aucune main ! Il semble moins agressif.");
                        transition(1);
                    }

                });
                performAction.start();
            } else {
                magicAttack();
            }
        } else if (choice == 3) {
            // Roar
            roar();
        } else {
            // Move away
            int travelDistance = randomGen.nextInt(3)+3;
            juliette.setDistance(juliette.getDistance() + travelDistance);
            if (juliette.getDistance() > 15) {
                flee();
            } else {
                moveAway(travelDistance);
            }
        }
    }

    /**
     * The Ptimo does a Magic Attack using the Ptimo class, does 1.5 * str damage to Juliette
     */
    private void magicAttack() {
        performAction.setInitialDelay(1000);
        gui.getPanelAnimation().doAnimation("Ptimo", 2, 1000);
        performAction.addActionListener(ej -> {
            performAction.removeActionListener(performAction.getActionListeners()[0]);
            performAction.stop();

            gui.getPanelAnimation().doAnimation("Juliette", 7, 500);

            int damage = currentPtimo.magicAttack(juliette);
            gui.getPanelStats().refresh();

            gui.getPanelDialogue().changeDialogue("Ptimo "+currentPtimo.getName()+" effectue une attaque magique sur "+juliette.getName()+" et lui inflige "+damage+" points de dégâts !");

            System.out.println("Juliette "+juliette);

            transition(1);
        });
        performAction.start();
    }

    /**
     * The Ptimo does an attack using the Ptimo class, does 1.0 * str damage to Juliette
     */
    private void attack() {
        performAction.setInitialDelay(1000);
        gui.getPanelAnimation().doAnimation("Ptimo", 1, 1000);
        performAction.addActionListener(ej -> {
            performAction.removeActionListener(performAction.getActionListeners()[0]);
            performAction.stop();

            gui.getPanelAnimation().doAnimation("Juliette", 7, 500);

            int damage = currentPtimo.attack(juliette);
            gui.getPanelStats().refresh();

            gui.getPanelDialogue().changeDialogue("Ptimo " + currentPtimo.getName() + " attaque "+juliette.getName()+" et lui inflige " + damage + " points de dégâts !");

            System.out.println("Juliette " + juliette);

            transition(1);
        });
        performAction.start();
    }

    /**
     * The Ptimo runs away, another fight starts soon after
     */
    public void flee() {
        // flee
        gui.getPanelDialogue().changeDialogue("Le Ptimo "+currentPtimo.getName()+" a fui !");
        gui.getPanelAnimation().ptimoSetLocation(1300,0,1);
        transition(1, 1);
    }

    /**
     * The Ptimo moves away from Juliette by the given int
     * @param travelDistance The distance in meters
     */
    private void moveAway(int travelDistance){
        gui.getPanelAnimation().julietteMove("X", -(travelDistance*35));
        gui.getPanelAnimation().julietteMove("X", 1);
        gui.getPanelAnimation().setJuliette_state(0);
        gui.getPanelDialogue().changeDialogue("Le Ptimo "+currentPtimo.getName()+" s'éloigne de "+travelDistance+" mètres !");
        transition(1);
    }

    /**
     * Uses the Ptimo class to roar, boosting the Ptimo's dominance by 20 and reducing its stress by 20
     */
    public void roar() {
        performAction.setInitialDelay(1000);
        gui.getPanelAnimation().doAnimation("Ptimo", 2, 1000);
        performAction.addActionListener(ej -> {
            performAction.removeActionListener(performAction.getActionListeners()[0]);
            performAction.stop();

            gui.getPanelDialogue().changeDialogue("Ptimo " + currentPtimo.getName() + " rugit de tout son être, il semble plus féroce !");
            currentPtimo.roar();

            transition(1);
        });
        performAction.start();
    }

    // ===== Getters / Setters =====
    public Juliette getJulietteInstance() {
        return juliette;
    }

    public Ptimo getPtimoInstance() {
        return currentPtimo;
    }
    /**
     * Game state
     * @return 0 - No controls / 1 - combat controls / 2 - Free movement
     */
    public int getGameState() {
        return gameState;
    }
    public void setGameState(int state) {
        this.gameState = state;
    }
}
