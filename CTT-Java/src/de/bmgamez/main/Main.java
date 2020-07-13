package de.bmgamez.main;

import de.bmgamez.backend.Downloader;
import de.bmgamez.backend.Getter;
import de.bmgamez.backend.Reader;
import de.bmgamez.backend.Sorter;
import org.json.simple.JSONArray;

import java.io.FileWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Main {

    public static void main(String[] args) throws Exception {

        Reader reader = new Reader();
        Sorter sorter = new Sorter();
        Downloader downloader = new Downloader();

        //downloader.downloadPlans();

        String input = reader.getOnlinePlan(Getter.get("path") + "/plan" + 0 + ".pdf");


        for (int i = 0; i < 2; i++) {

            JSONArray array = new JSONArray();

            //String input = reader.getOnlinePlan(Getter.get("path") + "/plan" + i + ".pdf");

            for (int j = 1; j <= 11; j++)

                try {
                    if (sorter.getLesson(input, j)[1] != null) {

                        array.add(sorter.getLesson(input, j)[1]);
                    } else {

                        array.add(reader.getPlan("resources/plan.CSV", i, j));
                    }
                } catch (Exception e) {
                    array.add(reader.getPlan("resources/plan.CSV", i, j));
                }

            FileWriter file = new FileWriter(Getter.get("path") + "/plan" + i + ".json");
            file.write(array.toJSONString());
            file.flush();

            System.out.println(array.toJSONString());
        }
    }
}