package com.codewithmonks.docmgmt.util;

import org.apache.tika.Tika;
import java.io.InputStream;

public class FileTextExtractor {

    private static final Tika tika = new Tika();

    public static String extractText(InputStream inputStream) {
        try {
            return tika.parseToString(inputStream);
        } catch (Exception e) {
            return "Could not extract text.";
        }
    }
}

