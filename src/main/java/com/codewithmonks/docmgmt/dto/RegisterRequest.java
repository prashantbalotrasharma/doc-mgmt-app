package com.codewithmonks.docmgmt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private String confirmPassword;
    private String role; // Optional: "ADMIN", "EDITOR", "VIEWER"
}

