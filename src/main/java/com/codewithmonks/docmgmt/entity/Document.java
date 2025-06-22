package com.codewithmonks.docmgmt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "documents")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;

    private String fileType;

    private String author;

    private String title;

    private LocalDate uploadDate;

    @Column(name = "file_data", columnDefinition = "BYTEA")
    private byte[] fileData;

//    @Lob
    @Column(columnDefinition = "TEXT")
//    @Basic(fetch = FetchType.EAGER)
    private String extractedText;
}

