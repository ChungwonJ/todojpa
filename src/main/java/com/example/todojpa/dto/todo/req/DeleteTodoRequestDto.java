package com.example.todojpa.dto.todo.req;

import lombok.Getter;

@Getter
public class DeleteTodoRequestDto {
    private final String password;

    public DeleteTodoRequestDto(String password) {
        this.password = password;
    }
}
