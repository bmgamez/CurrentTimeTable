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

        //Get the key for the authorization
        String body = getBody("https://schulinternes.de/dato40/server/hp-lazy-auth.php?data={\"schule\":\"" + Getter.get("school") + "\",\"user\":\"" + Getter.get("username") + "\",\"passwd\":\"" + Getter.get("password") + "\"}", false);

        //Get the links for the PDF files
        String body1 = getBody("https://schulinternes.de/dato40/server/hp-vertretungen-s.php?data=" + body, false);

        //Get the final link to download the PDF
        String realUrl = "https://schulinternes.de/dato40/server/hp-vertretungen-s.php?data=" + body;
        Document doc = Jsoup.connect(realUrl).get();
        Elements links = doc.select("a[href]");

        String finalURL = links.get(day).attr("abs:href");

        String path = Getter.get("path");

        try {

            // Download the final PDF file
            URL downloadUrl = new URL(finalURL);
            InputStream downloadIn = downloadUrl.openStream();
            FileOutputStream downloadFos = new FileOutputStream(path);

            int length = -1;
            byte[] buffer = new byte[1024];
            while ((length = downloadIn.read(buffer)) > -1) {
                downloadFos.write(buffer, 0, length);
            }

            downloadFos.close();
            downloadIn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return path;
    }

    public static String getBody(String stringUrl, boolean verbose) {

        String body = null;

        try {

            if (verbose) System.out.println(stringUrl);

            URL url = new URL(stringUrl);
            URLConnection con = url.openConnection();
            InputStream in = con.getInputStream();
            String encoding = con.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding;
            body = IOUtils.toString(in, encoding);

            if (verbose) System.out.println(body);

            in.close();

        } catch (Exception e) {
            System.out.println(e);
            return "false";
        }

        return body;
    }
}
