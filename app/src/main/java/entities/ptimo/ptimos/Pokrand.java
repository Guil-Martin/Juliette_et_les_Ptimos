package entities.ptimo.ptimos;

import java.util.Random;

public class Pokrand extends Ptimo {

    public Pokrand(String name) {
        super(name);
        this.type = Types.ROCK;

        setMaxHp(200);
        setHp(200);

        Random randGen = new Random();

        setStr((randGen.nextInt(9)+16));
        setStress((randGen.nextInt(6)+50));
        setDominance((randGen.nextInt(31)+70));
    }

}
