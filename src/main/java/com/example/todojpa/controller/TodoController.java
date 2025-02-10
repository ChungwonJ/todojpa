package com.example.todojpa.controller;

import com.example.todojpa.dto.todo.req.CreateTodoRequestDto;
import com.example.todojpa.dto.todo.res.TodoResponseDto;
import com.example.todojpa.service.TodoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponseDto> save(
            @RequestBody CreateTodoRequestDto requestDto,
            HttpServletRequest request
    ) {

        HttpSession session = request.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("username") : null;

        if (username == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        TodoResponseDto todoResponseDto = todoService.save(
                requestDto.getTitle(),
                requestDto.getContents(),
                username
        );

        return new ResponseEntity<>(todoResponseDto, HttpStatus.CREATED);
    }
}
