package entities.ptimo.ptimos;

import entities.Entity;

public abstract class Ptimo extends Entity {

    protected static int PTIMO;
    protected int number;
    protected int dominance;
    protected int stress;

    protected Types type;

    public Ptimo(String name) {
        super(name);

        Ptimo.PTIMO ++;
        this.number = Ptimo.PTIMO;
    }

    public void roar() {
        addDominance(20);
        addStress(-20);
    }

    /**
     * An attack, remove HP from the target depending of strength of the Ptimo
     * @param target Target entity
     * @return The damage done to the entity
     */
    public int attack(Entity target) {
        int hit = this.str;
        target.addHp(-hit);
        System.out.println(this.name + " porte un coup de " + hit + " point(s) a "+target.getName());
        return hit;
    }

    /**
     * A 1.5 more powerfull attack than the normal one
     * @param target Target entity
     * @return The damage done to the entity
     */
    public int magicAttack(Entity target) {
        int hit = this.str + (this.str/2);
        target.addHp(-hit);
        addDominance(15);
        System.out.println(this.name + " porte un coup de " + hit + " point(s) a "+target.getName());
        return hit;
    }

    // Getters Setters
    public int getNumber() {
        return number;
    }

    public Types getType() {
        return type;
    }

    public int getDominance() {
        return dominance;
    }
    public void setDominance(int dominance) {
        this.dominance = dominance;
    }
    public void addDominance(int dominance) {
        this.dominance += dominance;
        if (this.dominance > 100) this.dominance = 100;
        if (this.dominance < 0) this.dominance = 0;
    }

    public int getStress() {
        return stress;
    }
    public void setStress(int stress) {
        this.stress = stress;
    }
    public void addStress(int stress) {
        this.stress += stress;
        if (this.stress > 100) this.stress = 100;
        if (this.stress < 0) this.stress = 0;
    }

    @Override
    public String toString() {
        return "Ptimo{" +
                "name='" + name + '\'' +
                ", Type=" + type +
                ", hp=" + hp +
                ", maxHp=" + maxHp +
                ", Str=" + str +
                ", Stress=" + stress +
                ", Dominance=" + dominance +
                '}';
    }
}