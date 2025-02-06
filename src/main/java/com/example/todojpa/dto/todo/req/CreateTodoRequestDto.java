package com.example.todojpa.dto.todo.req;

import lombok.Getter;

@Getter
public class CreateTodoRequestDto {
    private final String username;
    private final String title;
    private final String contents;

    public CreateTodoRequestDto(String username, String title, String contents) {
        this.username = username;
        this.title = title;
        this.contents = contents;
    }
}
