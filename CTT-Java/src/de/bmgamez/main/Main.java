package de.bmgamez.main;

import de.bmgamez.backend.Downloader;
import de.bmgamez.backend.Getter;
import de.bmgamez.backend.Reader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Reader reader = new Reader();

        System.out.println(reader.searchPlan(Downloader.downloadPlan(Getter.get("day"))));

    }


}