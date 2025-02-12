package com.example.todojpa.controller;

import com.example.todojpa.dto.todo.req.CreateTodoRequestDto;
import com.example.todojpa.dto.todo.req.DeleteTodoRequestDto;
import com.example.todojpa.dto.todo.req.UpdateTodoRequestDto;
import com.example.todojpa.dto.todo.res.TodoResponseDto;
import com.example.todojpa.dto.todo.res.UpdateTodoResponseDto;
import com.example.todojpa.service.TodoService;
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

    private boolean userChecked(HttpServletRequest request, String todoUsername) {
        HttpSession session = request.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("username") : null;

        return username != null && username.equals(todoUsername);
    }

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
    public ResponseEntity<TodoResponseDto> findById(@PathVariable Long id, HttpServletRequest request) {
        TodoResponseDto todoResponseDto = todoService.findById(id);

        return new ResponseEntity<>(todoResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateTodoResponseDto> update(
            @PathVariable Long id,
            @RequestBody UpdateTodoRequestDto requestDto,
            HttpServletRequest request
    ) {
        TodoResponseDto todoResponseDto = todoService.findById(id);

        if (!userChecked(request, todoResponseDto.getUsername())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        UpdateTodoResponseDto updatedTodo = todoService.update(id, requestDto.getPassword(), requestDto.getTitle(), requestDto.getContents());
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @RequestBody DeleteTodoRequestDto requestDto,
            HttpServletRequest request
    ) {
        TodoResponseDto todoResponseDto = todoService.findById(id);

        if (!userChecked(request, todoResponseDto.getUsername())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        todoService.delete(id, requestDto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
