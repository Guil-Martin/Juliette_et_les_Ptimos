package entities.ptimo.ptimos;

import java.util.Random;

public class Sacbleu extends Ptimo {

    public Sacbleu(String name) {
        super(name);
        this.type = Types.GRASS;

        setMaxHp(100);
        setHp(100);

        Random randGen = new Random();
        setStr((randGen.nextInt(5)+8));

        // 20% chance to have dominant and less stressed sacbleu
        if (randGen.nextInt(101) < 20) {
            setStress((randGen.nextInt(16)+50));
            setDominance((randGen.nextInt(21))+60);
        } else {
            setStress((randGen.nextInt(21))+60);
            setDominance((randGen.nextInt(16))+50);
        }
    }


}
