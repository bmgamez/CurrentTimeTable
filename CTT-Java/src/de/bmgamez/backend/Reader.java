package de.bmgamez.backend;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Reader {

    public String getOnlinePlan(String destination) {

        String output = "";

        try {

            String plan = readPlan(destination);

            String[] lines = plan.split("\r\n|\r|\n");

            for (String line : lines) {

                if (line.contains(Getter.get("class"))) {

                    if (Getter.get("class") == "11" || Getter.get("class") == "12") {

                        ArrayList<String> courses = Getter.getArray("courses");

                        for (String course : courses) {

                            if (line.contains(course)) {

                                output += line + " /end ";
                            }
                        }
                    } else {
                        output += line + " /end ";
                    }
                }
            }

            if (output != "") {
                //Remove unnecessary two chars at the end
                output = output.substring(0, output.length() - 2);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(output);

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

            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            Date date = calendar.getTime();

            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

            int x = 0;

            if (dayOfWeek >= 6 || dayOfWeek <= 1) {
                if (day == 1) {
                    x = 0;
                } else if (day == 0) {
                    x = 8;
                }
            } else {
                switch (dayOfWeek) {
                    case 2:
                        x = 0 + (day * 2);
                        break;
                    case 3:
                        x = 2 + (day * 2);
                        break;
                    case 4:
                        x = 4 + (day * 2);
                        break;
                    case 5:
                        x = 6 + (day * 2);
                        break;
                }
            }

            thing = array[x];

        } catch (Exception e) {
            System.out.println(e);
            return "false";
        }

        return thing;
    }
}
