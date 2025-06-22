package com.codewithmonks.docmgmt.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DocumentFilterRequest {
    private String author;
    private String title;
    private String fileType;

    private LocalDate uploadDateFrom;
    private LocalDate uploadDateTo;

    private int page = 0;
    private int size = 10;
    private String sortBy = "uploadDate";
    private String sortDirection = "desc";
}

