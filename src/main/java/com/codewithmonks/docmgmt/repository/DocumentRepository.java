package com.codewithmonks.docmgmt.repository;

import com.codewithmonks.docmgmt.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Query(value = """
    SELECT d.* 
    FROM documents d 
    WHERE 
        LOWER(d.title) LIKE LOWER(CONCAT('%', :keyword, '%')) 
        OR LOWER(d.author) LIKE LOWER(CONCAT('%', :keyword, '%')) 
        OR LOWER(d.filename) LIKE LOWER(CONCAT('%', :keyword, '%')) 
        OR LOWER(d.extracted_text) LIKE LOWER(CONCAT('%', :keyword, '%'))
    """, nativeQuery = true)
    List<Document> searchDocuments(@Param("keyword") String keyword);
}

