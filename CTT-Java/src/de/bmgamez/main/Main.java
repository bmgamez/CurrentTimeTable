package de.bmgamez.main;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;

class Entry {
    int type;
    String[] klassen;
    String stunde;
    String fach;
    String neuesFach;
    String raum;
    String neuerRaum;
    String text;
}

public class Main {

    public static void main(String[] args) {

        String string = null;
        String string1 = "";

        try {

            File file = new File(Downloader.downloadPlan(Getter.get("day")));

            PDDocument pdDocument = PDDocument.load(file);
            PDFTextStripper pdfTextStripper = new PDFTextStripper();

            string = pdfTextStripper.getText(pdDocument);
            String[] lines = string.split("\r\n|\r|\n");

            for (String line : lines) {
                if (line.contains(Getter.get("class"))) {
                    string1 = string1 + line + "\n";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(string1);
    }
    public static Entry CreateEntry(String data) {
        if (String.contains(","))
        {

        }
    }
}
