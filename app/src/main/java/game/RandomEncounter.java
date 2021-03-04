package game;

import entities.ptimo.Juliette;
import entities.ptimo.ptimos.*;
import java.util.Random;

/**
 * Factory random encounter, it will return an Entity Ptimo depending on how many Ptimos juliette captured
 */
public class RandomEncounter {

    private static RandomEncounter randomEncounter;

    Random r = new Random();

    private RandomEncounter() {}

    public static RandomEncounter getEvents() {
        if (randomEncounter == null){
            randomEncounter = new RandomEncounter();
        }
        return randomEncounter;
    }

    /**
     * Get a random instance of a ptimo depending on some conditions
     * @param juliette instance
     * @return the instanced ptimo
     */
    public Ptimo getRandomEncounter(Juliette juliette) {

        int rand = r.nextInt(100) + 1;
        Ptimo ptimoEncounter;

        //return new Pokrand("Pokrand");

        // 40% chance of Pokrand apparition when Juliette has at least 5 Ptimos including at least a Saclbeu and a Pyralia
        if (juliette.getPtimoball().size() > 4 && juliette.hasPtimo("Sacbleu") && juliette.hasPtimo("Pyralia") && (rand < 40)) {
            ptimoEncounter = new Pokrand("Pokrand");
        } else {
            if (rand < 66){
                ptimoEncounter = new Sacbleu("Sacbleu");
            } else { // Le Pyralia est deux fois plus rare qu'un Sacbleu
                ptimoEncounter = new Pyralia("Pyralia");
            }
        }



        return ptimoEncounter;

    }


}
