package de.bmgamez.backend;

import java.util.ArrayList;

public class Sorter {

    public String[] getLesson(String data, String day, int hour) {

        String[] thing = new String[5];

        ArrayList<String> array = new ArrayList<String>();

        String[] lines = data.split("\r\n|\r|\n");

        for (String line : lines) {
            line = line.replace("D", "Deutsch");
            line = line.replace("Ph", "Physik");
            line = line.replace("Ch", "Chemie");
            line = line.replace("Aufs", "Aufsicht");
            line = line.replace("M", "Mathematik");
            line = line.replace("B", "Biologie");
            line = line.replace("E", "Englisch");
            line = line.replace("G", "Geschichte");
            line = line.replace("Geo", "Geographie");
            line = line.replace("SpM", "Sport");
            line = line.replace("SpW", "Sport");
            line = line.replace("Mus", "Musik");
            line = line.replace("F", "Französich");
            line = line.replace("GK", "Gemeinschaftskunde");
            line = line.replace("BK", "Bildende Kunst");
            line = line.replace(". / ", "./");
            line = line.replace(", ", ";");
            line = line.replace(" - ", "-");

            String[] lineSplitted = line.split(" ");

            if (lineSplitted[1].contains(String.valueOf(hour))) {
                if (lineSplitted[1].contains("-")) {
                    thing[0] = String.valueOf(hour);
                } else {
                    thing[0] = lineSplitted[1];
                }
                if (lineSplitted[2].contains("→")) {
                    thing[1] = lineSplitted[2].split("→")[1];
                } else {
                    thing[1] = lineSplitted[2];
                }
                if (lineSplitted[3].contains("→")) {
                    thing[2] = lineSplitted[3].split("→")[1];
                } else {
                    thing[2] = lineSplitted[3];
                }
                if (thing[2].equalsIgnoreCase("---")) {
                    thing[1] = "";
                    thing[2] = "";
                }
                try {
                    thing[3] = lineSplitted[4];
                } catch (Exception e) {
                    thing[3] = "";
                }
                try {
                    thing[4] = lineSplitted[5];
                } catch (Exception e) {
                    thing[4] = "";

                }
            }
        }

        return thing;
    }
}
