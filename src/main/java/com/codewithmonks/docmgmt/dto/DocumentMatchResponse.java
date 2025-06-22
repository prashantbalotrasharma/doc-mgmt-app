package com.codewithmonks.docmgmt.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class DocumentMatchResponse {
    private Long documentId;
    private String title;
    private String snippet;
    private String filename;
    private String author;
    private LocalDate uploadDate;
}

