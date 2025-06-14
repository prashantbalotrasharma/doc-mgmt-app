package com.codewithmonks.docmgmt.controller;

import com.codewithmonks.docmgmt.dto.LoginRequest;
import com.codewithmonks.docmgmt.dto.LoginResponse;
import com.codewithmonks.docmgmt.dto.RegisterRequest;
import com.codewithmonks.docmgmt.dto.RegisterResponse;
import com.codewithmonks.docmgmt.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}

