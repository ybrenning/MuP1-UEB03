package Menueplanung;

import java.util.*;

public class Gericht implements Comparable<Gericht>{
    private List<Zutat> zutaten;
    private double preis;

    public Gericht() {
        this.zutaten = new ArrayList<>();
        this.preis = 1.0;
    }

    public void addZutat(Zutat zutat) {
        zutaten.add(zutat);
        preis += zutat.getPreis();
    }

    public boolean hatZutat(Zutat zutat) {
        for (int i = 0; i < zutaten.size(); i++) {
            if (zutaten.get(i).equals(zutat)) {
                return true;
            }
        }

        return false;
    }

    public String getName() {
        StringBuilder sb = new StringBuilder();
        String threeLetters;
        for (int i = 0; i < zutaten.size(); i++) {
            threeLetters = zutaten.get(i).getName().substring(0, 3);
            sb.append(threeLetters);
        }

        return sb.toString();
    }

    public boolean istVegan() {
        for (int i = 0; i < zutaten.size(); i++) {
            if (! (zutaten.get(i).getVegan())) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int compareTo(Gericht vergleichsobjekt) {
        if (preis < vergleichsobjekt.preis) {
            return -1;
        } else if (preis == vergleichsobjekt.preis) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName()).append(" ");

        if (istVegan()) sb.append("(vegan) ");

        for (int i = 0; i < zutaten.size(); i++) {
            sb.append(zutaten.get((i)).getName()).append(" ");
        }

        sb.append(String.format("%.2f", preis)).append(" Euro");
        return sb.toString();
    }
}
