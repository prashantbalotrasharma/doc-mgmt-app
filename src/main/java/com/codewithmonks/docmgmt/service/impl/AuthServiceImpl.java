package com.codewithmonks.docmgmt.service.impl;

import com.codewithmonks.docmgmt.dto.LoginRequest;
import com.codewithmonks.docmgmt.dto.LoginResponse;
import com.codewithmonks.docmgmt.dto.RegisterRequest;
import com.codewithmonks.docmgmt.dto.RegisterResponse;
import com.codewithmonks.docmgmt.entity.User;
import com.codewithmonks.docmgmt.enums.Role;
import com.codewithmonks.docmgmt.exception.custom.EmailAlreadyExistsException;
import com.codewithmonks.docmgmt.exception.custom.UsernameAlreadyExistsException;
import com.codewithmonks.docmgmt.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import com.codewithmonks.docmgmt.repository.UserRepository;
import com.codewithmonks.docmgmt.security.JwtService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<RegisterResponse> register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already taken.");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already registered.");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole() != null ? Role.valueOf(request.getRole()) : Role.VIEWER)
                .build();

        userRepository.save(user);

        return ResponseEntity.created(URI.create("/api/v1/users/" + user.getId()))
                .body(RegisterResponse.builder()
                        .userId(user.getId())
                        .username(user.getUsername())
                        .role(user.getRole().name())
                        .message("User registered successfully.")
                        .build()
                );
    }

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        String role = auth.getAuthorities().stream().findFirst().orElseThrow().getAuthority();
        log.info("User logged in with role: {}", role);

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(
                LoginResponse.builder()
                        .username(user.getUsername())
                        .role(user.getRole().name())
                        .token(token)
                        .message("Login successful")
                        .build()
        );
    }
}

