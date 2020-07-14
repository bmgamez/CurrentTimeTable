package de.bmgamez.currenttimetable.main;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Downloader downloader = new Downloader();
        Reader reader = new Reader();

        // try {

        //     downloader.downloadPlans();
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }

        try {

            String rawText = reader.readPdf("resources/plan0.pdf");
            List<String> information = reader.getImportantInformation(rawText);
            reader.parseAndWriteInformation(information);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}