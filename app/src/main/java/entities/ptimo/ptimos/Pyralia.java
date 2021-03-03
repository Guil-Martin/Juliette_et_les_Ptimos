package entities.ptimo.ptimos;

import java.util.Random;

public class Pyralia extends Ptimo {

    public Pyralia(String name) {
        super(name);
        this.type = Types.FIRE;

        setMaxHp(150);
        setHp(150);

        Random randGen = new Random();

        setStr((randGen.nextInt(7)+10));

        setStress((randGen.nextInt(11)+50));
        setDominance((randGen.nextInt(21))+60);
    }

    // Le Pyralia est deux fois plus rare qu'un Sacbleu. Celui-ci est nettement moins stressé que le précédent mais beaucoup plus dominant !

}
