package com.codewithmonks.docmgmt.controller;

import com.codewithmonks.docmgmt.dto.DocumentFilterRequest;
import com.codewithmonks.docmgmt.dto.DocumentMetadataDTO;
import com.codewithmonks.docmgmt.entity.Document;
import com.codewithmonks.docmgmt.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/filter")
    public ResponseEntity<Page<Document>> filterDocuments(@RequestBody DocumentFilterRequest request) {
        Page<Document> docs = documentService.filterDocuments(request);
        return ResponseEntity.ok(docs);
    }
}

