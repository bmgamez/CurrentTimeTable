package de.bmgamez.currenttimetable.main;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {

    public String readPdf(String fileName) throws IOException {

        // Loading an existing document
        File file = new File(fileName);
        PDDocument document = PDDocument.load(file);

        // Instantiate PDFTextStripper class
        PDFTextStripper pdfStripper = new PDFTextStripper();

        // Retrieving text from PDF document
        String text = pdfStripper.getText(document);

        // Closing the document
        document.close();

        return text;
    }

    public List<String> getImportantInformation(String rawText) {

        String individualLines[] = rawText.split("\\r?\\n");
        List<String> importantLines = new ArrayList<String>();
        String individualLine = null;

        for (int i = 0; i < individualLines.length; i++) {

            individualLine = individualLines[i];

            if (individualLine.startsWith("Kl.")) {

                for (int j = i; j < individualLines.length; j++) {

                    i++;
                    if (i < individualLines.length) {
                        individualLine = individualLines[i];
                    } else {
                        break;
                    }

                    if (Character.isDigit(individualLine.charAt(0))) {

                        importantLines.add(individualLine);
                    } else {
                        break;
                    }
                }
            }

        }

        return importantLines;
    }

    public boolean parseAndWriteInformation(List<String> rawInformation) {

        return true;
    }
}
