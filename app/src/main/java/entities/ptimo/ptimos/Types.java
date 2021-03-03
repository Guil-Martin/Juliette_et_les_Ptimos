package entities.ptimo.ptimos;

public enum Types {

    FIRE("Fire"),
    WATER("Water"),
    GRASS("Grass"),
    BUG("Bug"),
    ELECTRIC("Electric"),
    FLY("Fly"),
    GROUND("Ground"),
    ROCK("Rock"),
    STEEL("Steel"),
    POISON("Poison"),
    ICE("Ice"),
    DRAGON("Dragon"),
    FAIRY("Fairy");

    private static final int NB_TYPES = Types.values().length;
    private final String type;

    Types(String name) {
        this.type = name;
    }

    @Override
    public String toString() {
        return this.type;
    }

}
