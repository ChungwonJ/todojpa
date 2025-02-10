package com.example.todojpa.service;

import com.example.todojpa.dto.todo.res.TodoResponseDto;
import com.example.todojpa.entity.Todo;
import com.example.todojpa.entity.User;
import com.example.todojpa.repository.TodoRepository;
import com.example.todojpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    public TodoResponseDto save(String title, String contents, String username) {
        User findUser = userRepository.findUserByUsernameOrElseThrow(username);

        Todo todo = new Todo(title, contents);
        todo.setUser(findUser);

        todoRepository.save(todo);

        return new TodoResponseDto(todo.getId(),username, todo.getTitle(), todo.getContents());
    }
}
