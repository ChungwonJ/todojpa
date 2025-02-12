package com.example.todojpa.dto.comment.res;

import lombok.Getter;

@Getter
public class CommentResponseDto {
    private final Long id;
    private final String username;
    private final String content;

    public CommentResponseDto(Long id, String username, String content) {
        this.id = id;
        this.username = username;
        this.content = content;
    }
}
