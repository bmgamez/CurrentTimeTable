package de.bmgamez.backend;

import java.util.ArrayList;

public class Sorter {

    public String[] getLesson(String data, String day, int hour) {

        String[] thing = new String[5];

        ArrayList<String> array = new ArrayList<String>();

        String[] lines = data.split("\r\n|\r|\n");

        for (String line : lines) {
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
