package com.codewithmonks.docmgmt.controller;

import com.codewithmonks.docmgmt.dto.DocumentMetadataDTO;
import com.codewithmonks.docmgmt.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadDocument(
            @RequestParam("file") MultipartFile file,
            @RequestParam("author") String author,
            @RequestParam("title") String title
    ) throws IOException {
        DocumentMetadataDTO metadata = new DocumentMetadataDTO(author, title);
        return documentService.uploadDocument(file, metadata);
    }
}

