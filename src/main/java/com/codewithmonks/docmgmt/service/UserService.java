package com.codewithmonks.docmgmt.service;

import com.codewithmonks.docmgmt.dto.RegisterResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<RegisterResponse> promoteToAdmin(String username);
}
