package de.bmgamez.test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Test {

    public static void main(String[] args) {


        Calendar calendar = Calendar.getInstance();

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        System.out.println(dayOfWeek);

        //String[] string = reader.readPlan("resources/tmp/plan.pdf").split("\r\n|\r|\n");
        //System.out.println(Arrays.toString(string));

        //String string = reader.getOnlinePlan(Downloader.downloadPlan(Integer.parseInt(Getter.get("day"))));
        //for (int i = 1; i <= 11; i++) {
        //    try {
        //        if (sorter.getLesson(reader.getOnlinePlan(Getter.get("path")), Getter.get("dayText"), i)[0] == null) {
        //            System.out.println(reader.getPlan("resources/plan.CSV", Getter.get("dayText"), i));
        //        } else {
        //            System.out.println(sorter.getLesson(reader.getOnlinePlan(Getter.get("path")), Getter.get("dayText"), i)[1]);
        //        }
        //    } catch (Exception e) {
        //        System.out.println(reader.getPlan("resources/plan.CSV", Getter.get("dayText"), i));
        //    }
        //}

        //String[] string = reader.getOnlinePlan(Downloader1.downloadPlan(Integer.parseInt(Getter.get("day")))).split("\r\n|\r|\n");
        //for (String data : string) {
        //    Entry temp = Entry.CreateEntry(data);
        //    if (temp.stunde.contains("-")) {
        //        String[] tempArray = temp.stunde.split("-");
        //        entryArray[Integer.parseInt(tempArray[0])] = temp;
        //        entryArray[Integer.parseInt(tempArray[1])] = temp;
        //    } else {
        //        entryArray[Integer.parseInt(temp.stunde) - 1] = temp;
        //    }
        //}
        //
        //for (int i = 1; i <= 9; i++) {
        //    try {
        //        if (entryArray[i - 1].neuesFach == null) {
        //            System.out.println(reader.getPlan("resources/plan.CSV", Getter.get("dayText"), i));
        //        } else {
        //            System.out.println(entryArray[i - 1].neuesFach);
        //        }
        //    } catch (NullPointerException e) {
        //        System.out.println(reader.getPlan("resources/plan.CSV", Getter.get("dayText"), i));
        //    }
        //}

    }
}
