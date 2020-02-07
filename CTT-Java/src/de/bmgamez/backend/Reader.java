package de.bmgamez.backend;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {

    public String getOnlinePlan(String destination) {

        String output = "";

        try {

            String plan = readPlan(destination);

            String[] lines = plan.split("\r\n|\r|\n");

            for (String line : lines) {

                if (Getter.getArray("courses").toArray().length == 0) {

                    if (line.contains(Getter.get("class"))) {

                        output = output + line + "\n";
                    }
                } else {

                    if (line.contains(Getter.get("class"))) {

                        output += line;

                    } else {

                        ArrayList<String> courses = Getter.getArray("courses");

                        for (String course : courses) {

                            if (line.contains(course)) {

                                output += line;
                            }
                        }
                    }
                }
            }

            //Remove unnecessary two chars at the end
            output = output.substring(0, output.length() - 2);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output;
    }

    public String readPlan(String destination) {

        String string = null;

        try {

            File file = new File(destination);

            PDDocument pdDocument = PDDocument.load(file);
            PDFTextStripper pdfTextStripper = new PDFTextStripper();

            string = pdfTextStripper.getText(pdDocument);

            pdDocument.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return string;
    }

    public String getPlan(String destination, int day, int hour) {

        String thing = null;
        ArrayList<String[]> arrayList = new ArrayList<String[]>();

        try {

            BufferedReader fileReader = new BufferedReader(new FileReader(destination));

            String line;

            while ((line = fileReader.readLine()) != null) {
                arrayList.add(line.split(";"));
            }

            String[] array = arrayList.get(hour);

            int x = 0;

            if ((day % 2) == 1) {
                x = day + 2 - 1;
            } else if ((day % 2) == 0) {
                x = day + 2;
            }

            thing = array[x];

        } catch (Exception e) {
            System.out.println(e);
            return "false";
        }

        return thing;
    }
}
