package com.codewithmonks.docmgmt.util;

import com.codewithmonks.docmgmt.exception.custom.InvalidFileFormatException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileParserUtil {

    public static String extractText(MultipartFile file) throws IOException {
        String contentType = file.getContentType();

        if (contentType.equals("text/plain")) {
            return new String(file.getBytes(), StandardCharsets.UTF_8);

        } else if (contentType.equals("application/pdf")) {
            try (PDDocument document = PDDocument.load(file.getInputStream())) {
                PDFTextStripper stripper = new PDFTextStripper();
                return stripper.getText(document);
            }

        } else if (contentType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
            try (XWPFDocument doc = new XWPFDocument(file.getInputStream())) {
                XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
                return extractor.getText();
            }

        } else {
            throw new InvalidFileFormatException("Unsupported file type: " + contentType);
        }
    }
}

