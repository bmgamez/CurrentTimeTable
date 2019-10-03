package de.bmgamez.main;

import de.bmgamez.backend.Downloader;
import de.bmgamez.backend.Getter;
import de.bmgamez.backend.Reader;
import de.bmgamez.backend.Sorter;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Reader reader = new Reader();
        Sorter sorter = new Sorter();

        //System.out.println(reader.getOnlinePlan(Downloader.downloadPlan(Getter.get("day"))));

        //System.out.println(reader.getPlan("resources/plan.csv", "mo", 1));


        System.out.println(Arrays.toString(sorter.getLesson(reader.getOnlinePlan(Getter.get("path")), Getter.get("day"), 5)));

        for (int i = 1; i <= 11; i++) {
            if (sorter.getLesson(Downloader.downloadPlan(Getter.get("day")), Getter.get("day"), i)[0] == null) {
                System.out.println(reader.getPlan("resources/plan.CSV", Getter.get("day"), i));
            } else {
                System.out.println(sorter.getLesson(Downloader.downloadPlan(Getter.get("day")), Getter.get("day"), i)[1]);
            }
        }

    }


}