package com.example.todojpa.dto.todo.res;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateTodoResponseDto {
    private final String title;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public UpdateTodoResponseDto(String title, String contents, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
