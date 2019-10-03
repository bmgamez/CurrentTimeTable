package de.bmgamez.backend;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Reader {

    public String getOnlinePlan(String destination) {

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

    public String getPlan(String destination, String day, int hour) {

        String thing = null;
        ArrayList<String[]> arrayList = new ArrayList<String[]>();

        try {

            BufferedReader fileReader = new BufferedReader(new FileReader(destination));

            String line;

            while ((line = fileReader.readLine()) != null) {
                arrayList.add(line.split(";"));
            }

            String[] array = arrayList.get(hour);

            int x;

            switch (day) {
                case "mo":
                    x = 0;
                    break;
                case "di":
                    x = 2;
                    break;
                case "mi":
                    x = 4;
                    break;
                case "do":
                    x = 6;
                    break;
                case "fr":
                    x = 8;
                    break;
                default:
                    return "";
            }

            thing = array[x];

        } catch (Exception e) {
            System.out.println(e);
            return "false";
        }


        return thing;
    }
}
