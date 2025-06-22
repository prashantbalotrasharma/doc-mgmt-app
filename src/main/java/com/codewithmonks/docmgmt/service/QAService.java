package com.codewithmonks.docmgmt.service;

import com.codewithmonks.docmgmt.dto.DocumentMatchResponse;
import com.codewithmonks.docmgmt.entity.Document;
import com.codewithmonks.docmgmt.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QAService {

    private final DocumentRepository documentRepository;

    public List<DocumentMatchResponse> answerQuestion(String question) {
        // very basic keyword extraction (can be improved with NLP later)
        String keyword = question.trim().split("\\s+")[0]; // Just first keyword

        List<Document> docs = documentRepository.searchDocuments(keyword);

        return docs.stream().map(doc -> {
            String snippet = extractSnippet(doc.getExtractedText(), keyword);
            return DocumentMatchResponse.builder()
                    .documentId(doc.getId())
                    .title(doc.getTitle())
                    .filename(doc.getFilename())
                    .author(doc.getAuthor())
                    .uploadDate(doc.getUploadDate())
                    .snippet(snippet)
                    .build();
        }).toList();
    }

    private String extractSnippet(String text, String keyword) {
        if (text == null) return "";
        int index = text.toLowerCase().indexOf(keyword.toLowerCase());
        if (index == -1) return text.substring(0, Math.min(150, text.length())) + "...";

        int start = Math.max(index - 50, 0);
        int end = Math.min(index + 100, text.length());
        return text.substring(start, end) + "...";
    }
}

