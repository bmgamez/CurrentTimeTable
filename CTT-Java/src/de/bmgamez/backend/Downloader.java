package de.bmgamez.backend;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

public class Downloader {

    public static String downloadPlan(String day) {

        String path = Getter.get("path");

        try {
            URL url = new URL("https://schulinternes.de/dato40/server/hp-show.php?data=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzY2h1bGUiOiI2ODc2NmNmZyIsImV4cCI6MTU3MzgzOTQ5MSwidGFnIjoibW8ifQ.kzn60OPDnq4TCxRZ1n_IEToghvZVZObGg69ZJTSonrQ");
            InputStream in = url.openStream();
            FileOutputStream fos = new FileOutputStream(path);
            System.out.println("Now Downloading");

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
