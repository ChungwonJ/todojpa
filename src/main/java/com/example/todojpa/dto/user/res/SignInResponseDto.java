package com.example.todojpa.dto.user.res;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SignInResponseDto {
    private final String username;
    private final String email;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public SignInResponseDto(String username, String email, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
