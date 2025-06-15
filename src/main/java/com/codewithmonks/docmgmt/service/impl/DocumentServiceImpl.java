package com.codewithmonks.docmgmt.service.impl;

import com.codewithmonks.docmgmt.dto.DocumentMetadataDTO;
import com.codewithmonks.docmgmt.dto.UploadResponse;
import com.codewithmonks.docmgmt.entity.Document;
import com.codewithmonks.docmgmt.repository.DocumentRepository;
import com.codewithmonks.docmgmt.service.DocumentService;
import com.codewithmonks.docmgmt.util.FileTextExtractor;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    @Transactional
    public ResponseEntity<?> uploadDocument(MultipartFile file, DocumentMetadataDTO metadataDTO) {
        String fileType = file.getContentType();
        List<String> allowedTypes = List.of("application/pdf", "image/png", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");

        if (!allowedTypes.contains(fileType)) {
            return ResponseEntity.badRequest().body("Unsupported file type.");
        }

        try {
            byte[] fileBytes = file.getBytes();

            String extractedText = FileTextExtractor.extractText(file.getInputStream());

            Document doc = Document.builder()
                    .filename(file.getOriginalFilename())
                    .fileType(file.getContentType())
                    .fileData(fileBytes)
                    .extractedText(extractedText)
                    .uploadDate(LocalDate.now())
                    .title(metadataDTO.getTitle())
                    .author(metadataDTO.getAuthor())
                    .build();

            documentRepository.save(doc);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(UploadResponse.builder()
                            .message("Document uploaded successfully.")
                            .documentId(doc.getId())
                            .filename(doc.getFilename())
                            .build());

        } catch (IOException e) {
            log.error("File upload failed: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("File upload failed due to an internal error.");
        }
    }

}

