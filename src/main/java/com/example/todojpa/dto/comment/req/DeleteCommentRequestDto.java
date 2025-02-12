package com.example.todojpa.dto.comment.req;

import lombok.Getter;

@Getter
public class DeleteCommentRequestDto {
    private final String password;

    public DeleteCommentRequestDto(String password) {
        this.password = password;
    }
}
