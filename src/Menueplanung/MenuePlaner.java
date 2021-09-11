package Menueplanung;

import java.util.*;

public class MenuePlaner {
    private List<Zutat> zutatenListe;
    private Map<String,List<Gericht>> speiseplan;

    public MenuePlaner(Zutat[] zutatenArray) {
        zutatenListe = new ArrayList<Zutat>(Arrays.asList(zutatenArray));
        speiseplan = new HashMap<>();
    }

    private int zufallszahl(int minimum, int maximum) {
        return (int)(Math.random() * (maximum + 1 - minimum)) + minimum;
    }

    private Gericht erstelleGericht() {
        int anzahlZutaten = zufallszahl(3, 5);
        int hinzugefuegt = 0;
        Gericht gericht = new Gericht();

        while (hinzugefuegt < anzahlZutaten) {
            int rnd = new Random().nextInt(zutatenListe.size());
            Zutat zutat = zutatenListe.get(rnd);
            if (! gericht.hatZutat(zutat)) {
                gericht.addZutat(zutat);
                hinzugefuegt++;
            }
        }

        return gericht;
    }

    private void erstelleMenueFuerWochentag(String tag, int anzahlGerichte) {
        List<Gericht> gerichte = new ArrayList<Gericht>();

        for (int i = 0; i < anzahlGerichte; i++) {
            gerichte.add(erstelleGericht());
        }

        speiseplan.put(tag, gerichte);
    }

    public void erstelleSpeiseplan() {
        erstelleMenueFuerWochentag("Montag", 4);
        erstelleMenueFuerWochentag("Dienstag", 4);
        erstelleMenueFuerWochentag("Mittwoch", 5);
    }

    public String getMenueFuerWochentag(String tag) {
        StringBuilder sb = new StringBuilder();
        if (speiseplan.containsKey(tag)) {
            sb.append(tag).append(":\n");
        } else return ("Die Mensa hat geschlossen!");

        List<Gericht> dieserTag = speiseplan.get(tag);
        Collections.sort(dieserTag);
        for (int i = 0; i < dieserTag.size(); i++) {
            sb.append(dieserTag.get(i).toString()).append("\n");
        }

        return sb.toString();
    }
}
