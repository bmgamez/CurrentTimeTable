package de.bmgamez.main;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;

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
                    Entry entry = new Entry();
                    entry.CreateEntry(line);
                    System.out.println(entry.stunde);
                    string1 = string1 + line + "\n";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(string1);
    }
}
