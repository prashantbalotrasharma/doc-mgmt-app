package com.codewithmonks.docmgmt.service.impl;

import com.codewithmonks.docmgmt.dto.RegisterResponse;
import com.codewithmonks.docmgmt.entity.User;
import com.codewithmonks.docmgmt.enums.Role;
import com.codewithmonks.docmgmt.repository.UserRepository;
import com.codewithmonks.docmgmt.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URI;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<RegisterResponse> promoteToAdmin(@PathVariable String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setRole(Role.ADMIN);
        userRepository.save(user);

        return ResponseEntity.created(URI.create("/api/v1/users/" + user.getId()))
                .body(RegisterResponse.builder()
                        .userId(user.getId())
                        .username(user.getUsername())
                        .role(user.getRole().name())
                        .message("Promoted to ADMIN.")
                        .build()
                );
    }
}
