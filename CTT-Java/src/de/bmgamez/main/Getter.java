package de.bmgamez.main;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.Reader;

public class Getter {

    static String get(String what) {

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
}
