package com.codewithmonks.docmgmt.util;

import com.codewithmonks.docmgmt.dto.DocumentFilterRequest;
import com.codewithmonks.docmgmt.entity.Document;
import org.springframework.data.jpa.domain.Specification;

public class DocumentSpecification {

    public static Specification<Document> build(DocumentFilterRequest request) {
        return (root, query, cb) -> {
            var predicates = cb.conjunction();

            if (request.getAuthor() != null && !request.getAuthor().isBlank()) {
                predicates.getExpressions().add(cb.like(cb.lower(root.get("author")), "%" + request.getAuthor().toLowerCase() + "%"));
            }

            if (request.getTitle() != null && !request.getTitle().isBlank()) {
                predicates.getExpressions().add(cb.like(cb.lower(root.get("title")), "%" + request.getTitle().toLowerCase() + "%"));
            }

            if (request.getFileType() != null && !request.getFileType().isBlank()) {
                predicates.getExpressions().add(cb.equal(root.get("fileType"), request.getFileType()));
            }

            if (request.getUploadDateFrom() != null) {
                predicates.getExpressions().add(cb.greaterThanOrEqualTo(root.get("uploadDate"), request.getUploadDateFrom()));
            }

            if (request.getUploadDateTo() != null) {
                predicates.getExpressions().add(cb.lessThanOrEqualTo(root.get("uploadDate"), request.getUploadDateTo()));
            }

            return predicates;
        };
    }
}
