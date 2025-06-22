package com.codewithmonks.docmgmt.controller;

import com.codewithmonks.docmgmt.dto.DocumentMatchResponse;
import com.codewithmonks.docmgmt.dto.QuestionRequest;
import com.codewithmonks.docmgmt.service.QAService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/qa")
@RequiredArgsConstructor
public class QAController {

    private final QAService qaService;

    @PostMapping("/query")
    public ResponseEntity<List<DocumentMatchResponse>> query(@RequestBody QuestionRequest request) {
        List<DocumentMatchResponse> matches = qaService.answerQuestion(request.getQuestion());
        return ResponseEntity.ok(matches);
    }
}

