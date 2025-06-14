package com.codewithmonks.docmgmt.service;

import com.codewithmonks.docmgmt.dto.LoginRequest;
import com.codewithmonks.docmgmt.dto.LoginResponse;
import com.codewithmonks.docmgmt.dto.RegisterRequest;
import com.codewithmonks.docmgmt.dto.RegisterResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<RegisterResponse> register(RegisterRequest request);
    ResponseEntity<LoginResponse> login(LoginRequest request);
}

