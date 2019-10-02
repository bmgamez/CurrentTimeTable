package de.bmgamez.main;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

public class Downloader {

    static String downloadPlan(String day) {

        String path = Getter.get("path");

        try {
            URL url = new URL("http://schulinternes.de/dato40/hp-show.php?schule=" + Getter.get("school") + "&tag=" + day);
            InputStream in = url.openStream();
            FileOutputStream fos = new FileOutputStream(path);

            int length = -1;
            byte[] buffer = new byte[1024];
            while ((length = in.read(buffer)) > -1) {
                fos.write(buffer, 0, length);
            }

            fos.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return path;
    }
}
