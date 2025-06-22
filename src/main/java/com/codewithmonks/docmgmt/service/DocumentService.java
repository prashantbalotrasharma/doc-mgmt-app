package com.codewithmonks.docmgmt.service;

import com.codewithmonks.docmgmt.dto.DocumentFilterRequest;
import com.codewithmonks.docmgmt.dto.DocumentMetadataDTO;
import com.codewithmonks.docmgmt.entity.Document;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DocumentService {
    ResponseEntity<?> uploadDocument(MultipartFile file, DocumentMetadataDTO metadata) throws IOException;

    Page<Document> filterDocuments(DocumentFilterRequest request);
}

