package game;

import entities.ptimo.ptimos.Ptimo;

import java.util.Random;

public class PtimoAI {

    private static PtimoAI ptimoAI;
    private Random randomGen;

    private PtimoAI() {}

    public static PtimoAI getInstance() {
        if (ptimoAI == null){
            ptimoAI = new PtimoAI();
            ptimoAI.randomGen = new Random();
        }
        return ptimoAI;
    }

    /**
     * Return the decision that the Ptimo will take in the form of a int
     * @param ptimo The Ptimo entity
     * @return An int that will be used in Game ptimoturn()
     */
    public int decision(Ptimo ptimo) {

        int stress = ptimo.getStress();
        int dominance = ptimo.getDominance();

        // Chance for the ptimo to Roar
        int chanceRoar = 20;

        // Fréquence de l'attaque en fonction de la dominance :
        int chanceOfAttack = 15;
        int chanceOfMAttack = 10;

        // Fréquence de l'éloignement en fonction du stress :
        int chanceOfMoveAway = 0;

        if (dominance > 99) {        // 100 (En permanance)
            chanceOfMAttack += 90;
        } else if (dominance > 55) { // 56+ (très fréquent)
            chanceOfAttack += 40;
            chanceOfMAttack += 30;  // 31-55 (assez fréquent)
        } else if (dominance > 30) {
            chanceOfAttack += 25;
        }                           // 0-30 (peu fréquent)

        if (stress > 85) {          // 85+ (jamais - devient très aggressif - augmente le risque d'attaque magique)
            chanceOfMAttack += 25;
        } else if (stress > 75) {   // 75-84 (assez fréquent)
            chanceOfMoveAway += 40;
        } else if (stress > 55) {   // 55-74 (peu fréquent)
            chanceOfMoveAway += 20;
        }                           // 0-54 (jamais)

        System.out.println("AI chanceOfMAttack - "+chanceOfMAttack);
        System.out.println("AI chanceRoar - "+chanceRoar);
        System.out.println("AI chanceOfAttack - "+chanceOfAttack);
        System.out.println("AI chanceOfMoveAway - "+chanceOfMoveAway);

        int rand = randomGen.nextInt(101);
        if (rand < chanceOfMAttack){
            return 2;
        } else if (rand < chanceRoar) {
            return 3;
        } else if (rand < chanceOfAttack) {
            return 1;
        } else if (rand < chanceOfMoveAway) {
            return 4;
        }

        return 0;
    }

    private int getDistanceMalus(int i) {


        return 0;
    }


}
