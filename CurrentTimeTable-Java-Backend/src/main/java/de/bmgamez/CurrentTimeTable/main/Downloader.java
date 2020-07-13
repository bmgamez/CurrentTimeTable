package de.bmgamez.currenttimetable.main;

import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;

public class Downloader {

    public void downloadPlans() throws IOException {

        final JSONObject data = new JSONObject();
        data.put("schule", Settings.getSetting("school"));
        data.put("user", Settings.getSetting("username"));
        data.put("passwd", Settings.getSetting("password"));

        final String dataString = data.toJSONString();
        System.out.println(dataString);

        final String keyUrl = "https://schulinternes.de/dato40/server/hp-lazy-auth.php?data=" + dataString;
        System.out.println(keyUrl);

        final Document keyDocument = Jsoup.connect(keyUrl).get();
        final String key = keyDocument.body().text();

        final String linkRetrievalUrl = "https://schulinternes.de/dato40/server/hp-vertretungen-s.php?data=" + key;
        System.out.println(linkRetrievalUrl);

        final Document linkRetrievalDocument = Jsoup.connect(linkRetrievalUrl).get();
        final Elements documentLinks = linkRetrievalDocument.select("a");

        final String generalDocumentLink = "https://schulinternes.de/dato40/server";

        final Calendar calendar = Calendar.getInstance();
        final int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        for (int i = 0; i < documentLinks.size(); i++) {

            final Element documentLink = documentLinks.get(i);

            final String documentLinkEnding = documentLink.attr("href");
            System.out.println(documentLinkEnding);

            final String documentPdfLink = generalDocumentLink + documentLink.attr("href").substring(1);
            System.out.println(documentPdfLink);

            if (dayOfWeek > 1 && dayOfWeek < 7) {

                download(documentPdfLink, String.format("resources/plan%s.pdf", i));
            } else {

                if (i == 0) {

                    download(documentPdfLink, String.format("resources/plan%s.pdf", 1));
                } else if (i == 1) {

                    download(documentPdfLink, String.format("resources/plan%s.pdf", 0));
                }
            }
        }
    }

    public void download(final String url, final String fileName) {

        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());

                FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            final byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
