package skill;

import entities.Entity;

public class Skill {

    protected Entity owner = null;
    protected String name;
    protected int cost;
    protected int damage;
    protected int slot;

    public Skill(String name, int cost, int damage) {
        this.name = name;
        cost = Math.max(cost, 0);
        this.cost = cost;
        damage = Math.max(damage, 0);
        this.damage = damage;
    }

    public void effect() {

    }

    public Entity getOwner(){
        return this.owner;
    }
    public void setOwner(Entity character){
        this.owner =  character;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name =  name;
    }

    public int getCost(){
        return this.cost;
    }
    public void setCost(int cost){
        this.cost = cost;
    }

    public int getDamage(){
        return this.damage;
    }
    public void setDamage(int damage){
        this.damage = damage;
    }

    public int getSlot(){
        return this.slot;
    }
    public void setSlot(int slot){
        this.slot = slot;
    }


}
