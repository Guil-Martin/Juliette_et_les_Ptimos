package entities;
import java.util.ArrayList;

import skill.Skill;

public abstract class Entity {

    private static int ENTITIES = 0;
    protected int number;

    protected String type = "No type";
    protected int gender;

    protected String name;
    protected int level = 1;
    protected int hp;
    protected int maxHp;
    protected int mp;
    protected int maxMp;
    protected int str;
    protected int def;
    protected int res;
    protected int critRate;

    protected ArrayList<Skill> skills;

    protected Entity(String name) {

        this.name = name;
        Entity.ENTITIES ++;

        skills = new ArrayList<Skill>();

    }

    public Entity() {
        // Default stats
        // level, hp, mp, str, def, res, critRate
        setStats(new int[]{1, 100, 60, 20, 12, 5, 16});
    }

    protected void setStats(int[] stats) {
        stats[0] = Math.max(stats[0], 1);
        this.level = stats[0];

        hp = Math.max(hp, 0);
        this.hp = stats[1];
        this.maxHp = stats[1];

        stats[2] = Math.max(stats[2], 0);
        this.mp = stats[2];
        this.maxMp = stats[2];

        stats[3] = Math.max(stats[3], 0);
        this.str = stats[3];

        stats[4] = Math.max(stats[4], 0);
        this.def = stats[4];

        stats[5] = Math.max(stats[5], 0);
        this.res = stats[5];

        stats[6] = Math.max(stats[6], 0);
        this.critRate = stats[6];
    }

    protected void levelUp() {

    }

    public void addSkill(String name, int cost, int power) {
        Skill skill = new Skill(name, cost, power);
        skill.setOwner(this);
        this.skills.add(skill);
    }

    public int attack(Entity target, int damage) {
        System.out.println(this.name+" attaque "+target.getName()+" pour "+damage+" points de dégâts!");
        return target.hit(damage);
    }

    private int hit(int damage) {
        System.out.println(this.name+" Hit  damage - "+damage);
        /*
        if (this.shield > 0) {
            this.shield -= damage;
            damage -= this.shield;
            if (this.shield < 0) this.shield = 0;
            System.out.println(this.name+" Hit damage after shield - "+damage);
        }
         */
        if (damage > 0) {
            addHp(-damage);;
            System.out.println(this.name+" Hit hp after damage - "+this.hp);
        }
        return damage;
    }

    public int damage(Entity target) {
        double coefficient = Math.random();
        int hit = (int) (this.str * coefficient);
        target.hp -= hit;
        System.out.println(this.name + " porte un coup de " + hit + " point(s) a "+target.name);
        return hit;
    }

    public String damage(Entity target, Skill special) {
        double coefficient = Math.random();
        System.out.println("special - "+special.getName()+" cost - "+special.getCost());
        String result = "";

        if (this.mp > special.getCost()) {
            this.mp -= special.getCost();
            int damage = special.getDamage() + (int) (this.str * coefficient);
            //int rand = (int) Math.random() * 100;
            int rand = (int)(Math.random()*100);
            boolean critical = rand < this.critRate;
            damage = critical ? damage * 2 : damage;
            result += critical ? "| CRITICAL |" : "";
            target.hp -= damage;
            result += this.name + " porte un coup spécial avec "+special.getName()
                    + " de " + damage + " point(s) a "+target.name;
        } else {
            result += "Not enough mana...";
        }
        return result;
    }

    public String victory() {
        return this.name + " a gagné ! (vie restante: " + this.getHp() + ")";
    }

    // === Getters setters
    public Skill getSkill(int slot) {
        return this.skills.get(slot);
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) { this.name = name; }

    public int getGender() {
        return gender;
    }
    public void setGender(int gender) { this.gender = gender; }

    public int getLevel() {
        return this.level;
    }
    public void setLevel(int level) { this.level = level; }

    public int getHp() { return this.hp; }
    public void setHp(int hp) {
        this.hp = hp;
        if (this.hp > this.maxHp) this.hp = this.maxHp;
        if (this.hp < 0) this.hp = 0;
    }
    public void addHp(int hp) {
        this.hp += hp;
        if (this.hp > this.maxHp) this.hp = this.maxHp;
        if (this.hp < 0) this.hp = 0;
    }
    public int getMaxHp() {
        return this.maxHp;
    }
    public void setMaxHp(int maxHp) { this.maxHp = maxHp; }

    public int getMp() {
        return this.mp;
    }
    public void setMp(int mp) {
        this.mp = mp;
        if (this.mp > this.maxMp) this.mp = this.maxMp;
        if (this.mp < 0) this.mp = 0;
    }
    public void addMp(int mp) {
        this.mp += mp;
        if (this.mp > this.maxMp) this.mp = this.maxMp;
        if (this.mp < 0) this.mp = 0;
    }
    public int getMaxMp() {
        return this.maxMp;
    }
    public void setMaxMp(int maxMp) { this.maxMp = maxMp; }

    public int getStr() {
        return this.str;
    }
    public void setStr(int str) {
        this.str = str;
    }

    public int getDef() {
        return this.def;
    }
    public void setDef(int def) {
        this.def = def;
    }

    public int getRes() {
        return this.res;
    }
    public void setRes(int res) {
        this.res = res;
    }

    public int getCritRate() {
        return this.critRate;
    }
    public void setCritRate(int critRate) {
        this.critRate = critRate;
    }

    public String getJob() {
        return "";
    }
    // ===

    @Override
    public String toString() {
        return "Entity{" +
                "number=" + number +
                ", type='" + type + '\'' +
                ", gender=" + gender +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", hp=" + hp +
                ", maxHp=" + maxHp +
                ", mp=" + mp +
                ", maxMp=" + maxMp +
                ", str=" + str +
                ", def=" + def +
                ", res=" + res +
                ", critRate=" + critRate +
                ", skills=" + skills +
                '}';
    }

}