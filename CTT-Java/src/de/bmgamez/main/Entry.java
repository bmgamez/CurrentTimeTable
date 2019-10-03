package de.bmgamez.main;

public class Entry {
    // Wenn keine 2. klasse vorhanden dann klassen[1] = null
    public String[] klassen = new String[2];
    public String stunde;
    public String fach;
    // Wenn kein anderes Fach dann ist neuesFach = null
    public String neuesFach;
    public String raum;
    // Wenn kein neuer Raum dann ist neuerRaum = null
    public String neuerRaum;
    // Wenn kein verlegungsdatum vorhanden dann verlegungsdaten = null
    public String verlegungsdaten;
    // Wenn kein Text vorhanden dann = null
    public String text;


    public static Entry CreateEntry(String data) {
        String[] splitData = data.split(" ");
        Entry currentEntry = new Entry();
        String tempStunde;
        int currentIndex = 0;
        int Length = 4;
        boolean mehrereKlassen = false;
        boolean verlegungsdatumVorhanden = false;
        boolean textVorhanden = false;
        if (data.contains(",")) {
            mehrereKlassen = true;
            Length++;
        }
        if (data.contains("/")) {
            verlegungsdatumVorhanden = true;
            Length = Length + 3;
        }
        if (splitData.length > Length) {
            textVorhanden = true;
            //Length++;
        }
        currentEntry.klassen[0] = splitData[currentIndex];
        currentIndex++;
        if (mehrereKlassen) {
            currentEntry.klassen[1] = splitData[currentIndex];
            currentIndex++;
        }
        currentEntry.stunde = splitData[currentIndex];
        currentIndex++;
        if (splitData[currentIndex].equals("-")) {
            tempStunde = currentEntry.stunde;
            currentIndex++;
            currentEntry.stunde = tempStunde + "-" + splitData[currentIndex];
        }
        if (splitData[currentIndex].contains("→")) {
            String[] tempSplit = splitData[currentIndex].split("→");
            currentEntry.fach = tempSplit[0];
            currentEntry.neuesFach = tempSplit[1];
            currentIndex++;
        } else {
            currentEntry.fach = splitData[currentIndex];
            currentIndex++;
        }
        if (splitData[currentIndex].contains("→")) {
            String[] tempSplit = splitData[currentIndex].split("→");
            currentEntry.raum = tempSplit[0];
            currentEntry.neuerRaum = tempSplit[1];
            currentIndex++;
        } else {
            currentEntry.raum = splitData[currentIndex];
            currentIndex++;
        }
        if (verlegungsdatumVorhanden) {
            currentEntry.verlegungsdaten = splitData[currentIndex] + splitData[currentIndex+1] + splitData[currentIndex+2];
            currentIndex = currentIndex + 3;
        }
        if (textVorhanden) {
            currentEntry.text = splitData[currentIndex];
            //currentIndex++;
        }
        return currentEntry;
    }
}
