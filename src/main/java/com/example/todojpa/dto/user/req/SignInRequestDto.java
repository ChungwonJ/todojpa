package com.example.todojpa.dto.user.req;

import lombok.Getter;

@Getter
public class SignInRequestDto {
    private final String email;
    private final String password;

    public SignInRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
