package com.example.todojpa.dto.user.res;

import lombok.Getter;

@Getter
public class SignInResponseDto {
    private final String username;
    private final String email;

    public SignInResponseDto(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
