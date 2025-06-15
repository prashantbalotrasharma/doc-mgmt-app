package com.codewithmonks.docmgmt.repository;

import com.codewithmonks.docmgmt.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}

