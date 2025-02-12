package com.example.todojpa.dto.todo.res;

import lombok.Getter;

@Getter
public class TodoResponseDto {
    private final Long id;
    private final String username;
    private final String title;
    private final String contents;

    public TodoResponseDto(Long id, String username, String title, String contents) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.contents = contents;
    }
}
