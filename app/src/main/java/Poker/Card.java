package Poker;

public class Card implements Comparable {

    private final String name;
    private final String symbol;
    private final String value;
    private final String shortcut;

    public Card(String s, String value) {

        shortcut = s + value;

        this.symbol = getSymbolString(s);
        this.value = getValueString(value);

        if (value.equals("1")) {
            this.name = "Ace of "+this.symbol;
        } else {
            this.name = setNameValue(this.value) + " of "+this.symbol;
        }

    }

    private String getSymbolString(String s) {
        return switch (s) {
            case "d" -> "Diamond";
            case "c" -> "Clubs";
            case "h" -> "Heart";
            case "s" -> "Spade";
            default -> "Not valid";
        };
    }

    private String getValueString(String s) {
        return switch (s) {
            case "j" -> "11";
            case "q" -> "12";
            case "k" -> "13";
            default -> s;
        };
    }

    private String setNameValue(String s) {
        return switch (s) {
            case "11" -> "Jack";
            case "12" -> "Queen";
            case "13" -> "King";
            case "1" -> "Ace";
            default -> s;
        };
    }


    // Getters setters
    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getValue() {
        return value;
    }

    public String getShortcut() {
        return shortcut;
    }

    @Override
    public String toString() {
        return "Carte{" +
                "name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", value='" + value + '\'' +
                ", shortcut='" + shortcut + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object c) {
        return Integer.parseInt(this.value) - Integer.parseInt(((Card)c).getValue());
    }
}
