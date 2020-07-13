package de.bmgamez.currenttimetable.main;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        Downloader downloader = new Downloader();
        Reader reader = new Reader();

        try {
            downloader.downloadPlans();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            reader.readPdf("resources/plan0.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
