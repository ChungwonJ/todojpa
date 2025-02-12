package com.example.todojpa.dto.comment.req;

import lombok.Getter;

@Getter
public class CreateCommentRequestDto {
    private final String content;

    public CreateCommentRequestDto(String content) {
        this.content = content;
    }
}
