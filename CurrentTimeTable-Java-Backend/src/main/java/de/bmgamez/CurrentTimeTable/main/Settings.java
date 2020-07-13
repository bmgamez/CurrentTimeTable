package de.bmgamez.currenttimetable.main;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;

public class Settings {

    private static String settingsFilePath = "resources/settings.json";

    public static String getSetting(String requestedSetting) {

        String settingToReturn = null;
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(settingsFilePath)) {

            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            settingToReturn = (String) jsonObject.get(requestedSetting);

        } catch (Exception e) {
            return null;
        }

        return settingToReturn;
    }

    public static ArrayList<String> getSettingArray(String what) {

        ArrayList<String> array = new ArrayList<String>();
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(settingsFilePath)) {

            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            JSONArray jsonArray = (JSONArray) jsonObject.get(what);
            Iterator iterator = jsonArray.iterator();

            while (iterator.hasNext()) {
                array.add((String) iterator.next());
            }

        } catch (Exception e) {
            return null;
        }

        return array;
    }
}
