package com.example.todojpa.service;

import com.example.todojpa.dto.todo.res.TodoResponseDto;
import com.example.todojpa.dto.todo.res.UpdateTodoResponseDto;
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

    public TodoResponseDto findById(Long id) {
        Todo findTodo = todoRepository.findByIdOrElseThrow(id);
        User writer = findTodo.getUser();

        return new TodoResponseDto(findTodo.getId(), writer.getUsername(), findTodo.getTitle(), findTodo.getContents());
    }

    public UpdateTodoResponseDto update(Long id, String password, String title, String contents) {
        Todo todo = todoRepository.findByIdOrElseThrow(id);

        if (!todo.getUser().getPassword().equals(password)) {
            throw new RuntimeException("패스워드가 일치하지 않습니다.");
        }

        todo.setTitle(title);
        todo.setContents(contents);
        todoRepository.save(todo);

        return new UpdateTodoResponseDto(todo.getTitle(), todo.getContents());
    }

    public void delete(Long id, String password) {
        Todo findTodo = todoRepository.findByIdOrElseThrow(id);

        // 비밀번호 검증
        if (!findTodo.getUser().getPassword().equals(password)) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        // Todo 삭제
        todoRepository.delete(findTodo);
    }
}
