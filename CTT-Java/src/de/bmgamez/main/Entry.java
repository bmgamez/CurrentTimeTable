package de.bmgamez.main;

public class Entry {
    int type;
    String[] klassen;
    String stunde;
    String fach;
    String neuesFach;
    String raum;
    String neuerRaum;
    String text;


    public static Entry CreateEntry(String data) {
        Entry currentEntry = new Entry();
        boolean mehrereKlassen = false;
        boolean verlegungsdatumVorhanden = false;
        boolean
        if (data.contains(","))
        {
            mehrereKlassen = true;
        }
        if (data.contains("/"))
        {
            verlegungsdatumVorhanden = true;
        }
    }
}
