package com.codewithmonks.docmgmt.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UploadResponse {
    private String message;
    private Long documentId;
    private String filename;
}

