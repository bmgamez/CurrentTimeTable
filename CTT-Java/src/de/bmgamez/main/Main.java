package de.bmgamez.main;

import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileInputStream;

import static org.apache.pdfbox.pdmodel.PDDocument.load;

public class Main {

    public static void main(String[] args) {

        String string = null;
        try {

            File file = new File("resources/plan.pdf");

            PDDocument pdDocument = PDDocument.load(file);
            PDFTextStripper pdfTextStripper = new PDFTextStripper();

            string = pdfTextStripper.getText(pdDocument);

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Text:" + string);
    }
}
