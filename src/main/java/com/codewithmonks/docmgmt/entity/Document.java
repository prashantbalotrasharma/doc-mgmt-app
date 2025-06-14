package com.codewithmonks.docmgmt.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "documents")
@Data
public class Document {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String author;
    private String type;
    private LocalDate uploadDate;

    @Lob
    private String content;
}

