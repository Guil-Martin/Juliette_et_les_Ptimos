package entities.ptimo;

import entities.Entity;
import entities.ptimo.ptimos.Ptimo;

import java.util.ArrayList;
import java.util.Random;

public class Juliette extends Entity {

    private static Juliette juliette;
    private int distance;
    private static ArrayList<Ptimo> ptimoballs;
    private int treats;
    private int potions;
    private int dart;

    Random randomGen = new Random();

    private Juliette() {
        super("Juliette");
        setMaxHp(100);
        setHp(100);
        this.treats = 30;
        this.potions = 5;
        this.dart = 1;
        ptimoballs = new ArrayList<>();
    }

    public static Juliette getJulietteInstance() {
        if (juliette == null) {
            juliette = new Juliette();
        }
        return juliette;
    }

    /**
     * Return a string of the observation of the given Ptimo by Juliette
     * @param ptimo The ptimo Juliette observes
     * @return a string of the observation of the given Ptimo by Juliette
     */
    public String checkPtimo(Ptimo ptimo) {

        String result = "Le Ptimo "+ptimo.getName()+" semble ";

        int stress = ptimo.getStress();
        int dominance = ptimo.getDominance();

        if (stress > 75) result += "paniqué";
        else if (stress > 50) result += "nerveux";
        else if (stress > 25) result += "méfiant";
        else result += "détendu";

        if ((stress < 25 && dominance > 25) || (stress > 25 && dominance < 25)) {
            result += " mais ";
        } else {
            result += " et ";
        }

        if (dominance > 75) result += "dangereux";
        else if (dominance > 50) result += "féroce";
        else if (dominance > 25) result += "neutre";
        else result += "inoffensif";

        result += ", il se trouve à "+this.distance+"m de Juliette.";

        return result;
    }

    public void throwTreat() {
        if (this.treats > 0) this.treats--;
    }

    public boolean fireDart() {
        if (this.dart > 0) {
            this.dart--;
            return true;
        }
        return false;
    }

    /**
     * Juliette will heal up from 30 to 45 HP
     * @return Return the value healed. 0 is not able to take a potion
     */
    public int drinkPotion() {
        int valueHealed = 0;
        if (this.potions > 0) {
            this.potions--;
            valueHealed = randomGen.nextInt(16)+30;
            addHp(valueHealed);
        }
        return valueHealed;
    }

    /**
     * Adds a captured primo to the list
     * @param ptimo The Ptimo to add to the list
     */
    public void addPtimo(Ptimo ptimo) {
        if (ptimoballs.size() < 10) {
            ptimoballs.add(ptimo);
        }
    }

    /**
     * Check if the ptimo is present if the ptimoballs
     * @param name The name of the ptimo
     * @return true if the correctly named Ptimo is found
     */
    public boolean hasPtimo(String name) {
        for (Ptimo ptimo : ptimoballs) {
            if (ptimo.getName().equals(name)) return true;
        }
        return false;
    }

    /**
     * Returns a distance for approaching the ptimo
     * @return The distance left after the movement
     */
    public int approach(int distance) {
        this.distance -= distance;
        return Math.max(this.distance, 0);
    }

    /**
     * How many ptimoballs are left
     * @return Returns how many ptimoballs are left as int
     */
    public int ptimoballLeft() {
        return 10 - ptimoballs.size();
    }

    //Getters setters
    public ArrayList<Ptimo> getPtimoball() {
        return ptimoballs;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
    public int getDistance() {
        return distance;
    }

    public int getTreats() {
        return treats;
    }
    public void setTreats(int treats) {
        this.treats = treats;
    }
    public void addTreats(int treats) {
        this.treats += treats;
    }

    public int getPotions() {
        return potions;
    }
    public void setPotions(int potions) {
        this.potions = potions;
    }
    public void addPotions(int potions) {
        this.potions += potions;
    }

    public int getDart() {
        return dart;
    }
    public void setDart(int dart) {
        this.dart = dart;
    }

    @Override
    public String toString() {
        return "Juliette{" +
                "name='" + name + '\'' +
                ", hp=" + getHp() +
                ", Max hp=" + maxHp +
                ", treats=" + treats +
                ", potions=" + potions +
                '}';
    }
}