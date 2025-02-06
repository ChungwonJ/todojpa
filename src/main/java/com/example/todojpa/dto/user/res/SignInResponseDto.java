package com.example.todojpa.dto.user.res;

import lombok.Getter;

@Getter
public class SignInResponseDto {
    private final Long id;
    private final String username;

    public SignInResponseDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
