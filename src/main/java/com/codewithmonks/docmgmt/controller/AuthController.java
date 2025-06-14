package com.codewithmonks.docmgmt.controller;

import com.codewithmonks.docmgmt.dto.LoginRequest;
import com.codewithmonks.docmgmt.dto.RegisterRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        // call authService.register
        return new ResponseEntity<>("Register", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // call authService.login
        return new ResponseEntity<>("Login", HttpStatus.OK);
    }
}

