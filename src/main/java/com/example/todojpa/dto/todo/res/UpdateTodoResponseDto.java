package com.example.todojpa.dto.todo.res;

import lombok.Getter;

@Getter
public class UpdateTodoResponseDto {
    private final String title;
    private final String contents;

    public UpdateTodoResponseDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
