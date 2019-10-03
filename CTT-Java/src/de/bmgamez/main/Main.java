package de.bmgamez.main;

import de.bmgamez.backend.Downloader;
import de.bmgamez.backend.Getter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println(searchPlan(Downloader.downloadPlan(Getter.get("day"))));

    }

    public static String searchPlan(String destination) {

        String string = null;
        String string1 = "";

        try {

            File file = new File(destination);

            PDDocument pdDocument = PDDocument.load(file);
            PDFTextStripper pdfTextStripper = new PDFTextStripper();

            string = pdfTextStripper.getText(pdDocument);
            String[] lines = string.split("\r\n|\r|\n");

            for (String line : lines) {
                if (Getter.getArray("courses").toArray().length == 0) {
                    if (line.contains(Getter.get("class"))) {
                        string1 = string1 + line + "\n";
                    }
                } else {
                    if (line.contains(Getter.get("class"))) {

                        ArrayList<String> arrayList = Getter.getArray("courses");
                        String[] courses = new String[arrayList.size()];
                        courses = arrayList.toArray(courses);

                        for (String course : courses) {
                            if (line.contains(course)) {
                                string1 = string1 + line + "\n";
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return (string1);
    }
}