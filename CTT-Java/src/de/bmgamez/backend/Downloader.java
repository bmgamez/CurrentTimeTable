package de.bmgamez.backend;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Downloader {

    public static String downloadPlan(int day) throws Exception {

        URL url = new URL("https://schulinternes.de/dato40/server/hp-lazy-auth.php?data={%22schule%22:%22" + Getter.get("school") + "%22,%22user%22:%22" + Getter.get("username") + "%22,%22passwd%22:%22" + Getter.get("password") + "%22}");
        URLConnection con = url.openConnection();
        InputStream in = con.getInputStream();
        String encoding = con.getContentEncoding();
        encoding = encoding == null ? "UTF-8" : encoding;
        String body = IOUtils.toString(in, encoding);

        URL url1 = new URL("https://schulinternes.de/dato40/server/hp-vertretungen-s.php?data=" + body);
        URLConnection con1 = url1.openConnection();
        InputStream in1 = con1.getInputStream();
        String encoding1 = con1.getContentEncoding();
        encoding1 = encoding1 == null ? "UTF-8" : encoding1;
        String body1 = IOUtils.toString(in1, encoding1);

        String realurl = "https://schulinternes.de/dato40/server/hp-vertretungen-s.php?data=" + body;
        Document doc = Jsoup.connect(realurl).get();
        Elements links = doc.select("a[href]");

        String finalURL = links.get(day).attr("abs:href");

        String path = Getter.get("path");

        try {
            URL url23 = new URL(finalURL);
            InputStream in23 = url23.openStream();
            FileOutputStream fos23 = new FileOutputStream(path);

            int length = -1;
            byte[] buffer = new byte[1024];
            while ((length = in23.read(buffer)) > -1) {
                fos23.write(buffer, 0, length);
            }

            fos23.close();
            in23.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return path;
    }
}
