package com.example.todojpa.dto.user.req;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdatePasswordRequestDto {
    private final String oldPassword;

    private final String newPassword;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public UpdatePasswordRequestDto(String oldPassword, String newPassword, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
