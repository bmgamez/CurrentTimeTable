package de.bmgamez.backend;

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
        int currentIndex = 0;
        int Length = 4;
        boolean fälltAus = false;
        boolean mehrereKlassen = false;
        boolean verlegungsdatumVorhanden = false;
        boolean textVorhanden = false;
        if (data.contains("---")) {
            fälltAus = true;
        }
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
            String tempStunde = currentEntry.stunde;
            currentIndex++;
            currentEntry.stunde = tempStunde + "-" + splitData[currentIndex];
            currentIndex++;
        }
        if (splitData[currentIndex].contains("→")) {
            String[] tempSplit = splitData[currentIndex].split("→");
            currentEntry.fach = tempSplit[0];
            currentEntry.neuesFach = tempSplit[1];
            currentIndex++;
        } else if (fälltAus) {
            currentEntry.fach = splitData[currentIndex];
            currentEntry.neuesFach = "---";
            currentIndex = currentIndex + 2;
        } else {
            currentEntry.fach = splitData[currentIndex];
            currentEntry.neuesFach = splitData[currentIndex];
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
            currentEntry.verlegungsdaten = splitData[currentIndex] + splitData[currentIndex + 1] + splitData[currentIndex + 2];
            currentIndex = currentIndex + 3;
        }
        if (textVorhanden) {
            currentEntry.text = splitData[currentIndex];
            //currentIndex++;
        }

        System.out.println(currentEntry.klassen[0]);
        System.out.println(currentEntry.klassen[1]);
        System.out.println(currentEntry.stunde);
        System.out.println(currentEntry.fach);
        System.out.println(currentEntry.neuesFach);
        System.out.println(currentEntry.raum);
        System.out.println(currentEntry.neuerRaum);
        System.out.println(currentEntry.verlegungsdaten);
        System.out.println(currentEntry.text);
        System.out.println("---------------------------");
        return currentEntry;
    }
}
