package com.codewithmonks.docmgmt.controller;

import com.codewithmonks.docmgmt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/users/{username}/promote")
    public ResponseEntity<?> promoteToAdmin(@PathVariable String username) {
        return userService.promoteToAdmin(username);
    }
}

