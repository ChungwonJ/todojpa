package com.example.todojpa.dto.comment.req;

import lombok.Getter;

@Getter
public class UpdateCommentRequestDto {
    private final String content;
    private final String password;

    public UpdateCommentRequestDto(String content, String password) {
        this.content = content;
        this.password = password;
    }
}
