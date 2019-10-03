package de.bmgamez.main;

import de.bmgamez.backend.Downloader;
import de.bmgamez.backend.Getter;
import de.bmgamez.backend.Reader;

public class Main {

    public static void main(String[] args) {

        Reader reader = new Reader();

        System.out.println(reader.getOnlinePlan(Downloader.downloadPlan(Getter.get("day"))));

        System.out.println(reader.getPlan("resources/plan.csv", "mo", 1));

    }


}