package de.bmgamez.main;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;

public class Getter {

    public static String get(String what) {

        String thing = null;
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader("resources/settings.json")) {

            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            thing = (String) jsonObject.get(what);

        } catch (Exception e) {
            return null;
        }

        return thing;
    }

    public static ArrayList<String> getArray(String what) {

        ArrayList<String> thing = new ArrayList<String>();
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader("resources/settings.json")) {

            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            JSONArray jsonArray = (JSONArray) jsonObject.get(what);
            Iterator iterator = jsonArray.iterator();

            while (iterator.hasNext()) {
                thing.add((String) iterator.next());
            }

        } catch (Exception e) {
            return null;
        }

        return thing;
    }
}
