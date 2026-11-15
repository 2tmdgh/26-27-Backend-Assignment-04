package com.example.security.domain.user.dto;

import com.example.security.domain.user.entity.Role;
import com.example.security.domain.user.entity.User;

public record UserResponse(
        Long id,
        String email,
        Role role
) {

    public static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getEmail(), user.getRole());
    }
}
