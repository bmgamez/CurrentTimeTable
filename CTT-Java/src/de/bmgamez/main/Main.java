package de.bmgamez.main;

import de.bmgamez.backend.*;

public class Main {

    public static void main(String[] args) {
        Entry[] entryArray = new Entry[11];

        Reader reader = new Reader();
        Sorter sorter = new Sorter();


        //Entry entry = Entry.CreateEntry("10c 2 M→G 404 Mi-2.10. / 9");
        //System.out.println(entry.neuesFach);

        System.out.println(reader.getOnlinePlan(Downloader.downloadPlan(Getter.get("day"))));

        //System.out.println(sorter.getLesson(reader.getOnlinePlan(Getter.get("path")), Getter.get("day"), 5));

        //String string = reader.getOnlinePlan(Downloader.downloadPlan(Getter.get("day")));

        //for (int i = 1; i <= 9; i++) {
        //    try {
        //        if (sorter.getLesson(reader.getOnlinePlan(Getter.get("path")), Getter.get("day"), i)[0] == null) {
        //            System.out.println(reader.getPlan("resources/plan.CSV", Getter.get("day"), i));
        //        } else {
        //            System.out.println(sorter.getLesson(reader.getOnlinePlan(Getter.get("path")), Getter.get("day"), i)[1]);
        //        }
        //    } catch (Exception e) {
        //        System.out.println(reader.getPlan("resources/plan.CSV", Getter.get("day"), i));
        //    }
        //}

        String[] string = reader.getOnlinePlan(Downloader.downloadPlan(Getter.get("day"))).split("\r\n|\r|\n");
        for (String data : string) {
            Entry temp = Entry.CreateEntry(data);
            if (temp.stunde.contains("-")) {
                String[] tempArray = temp.stunde.split("-");
                entryArray[Integer.parseInt(tempArray[0])] = temp;
                entryArray[Integer.parseInt(tempArray[1])] = temp;
            } else {
                entryArray[Integer.parseInt(temp.stunde) - 1] = temp;
            }
        }

        for (int i = 1; i <= 9; i++) {
            try {
                if (entryArray[i - 1].neuesFach == null) {
                    System.out.println(reader.getPlan("resources/plan.CSV", Getter.get("day"), i));
                } else {
                    System.out.println(entryArray[i - 1].neuesFach);
                }
            } catch (Exception e) {
                System.out.println(reader.getPlan("resources/plan.CSV", Getter.get("day"), i));
            }
        }
    }
}