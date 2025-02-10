package com.example.todojpa.controller;

import com.example.todojpa.dto.todo.req.CreateTodoRequestDto;
import com.example.todojpa.dto.todo.req.UpdateTodoRequestDto;
import com.example.todojpa.dto.todo.res.TodoResponseDto;
import com.example.todojpa.dto.todo.res.UpdateTodoResponseDto;
import com.example.todojpa.service.TodoService;
import com.example.todojpa.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;
    private final UserService userService;

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

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> findById(@PathVariable Long id) {
        TodoResponseDto todoResponseDto = todoService.findById(id);
        return new ResponseEntity<>(todoResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateTodoResponseDto> update(
            @PathVariable Long id,
            @RequestBody UpdateTodoRequestDto requestDto
    ) {
        UpdateTodoResponseDto updatedTodo = todoService.update(id, requestDto.getPassword(), requestDto.getTitle(), requestDto.getContents());
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }
}
