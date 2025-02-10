package com.example.todojpa.dto.todo.req;

import lombok.Getter;

@Getter
public class UpdateTodoRequestDto {
    private final String password;
    private final String title;
    private final String contents;

    public UpdateTodoRequestDto(String password, String title, String contents) {
        this.password = password;
        this.title = title;
        this.contents = contents;
    }
}
