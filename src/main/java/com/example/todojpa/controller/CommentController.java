package com.example.todojpa.controller;

import com.example.todojpa.dto.comment.req.CreateCommentRequestDto;
import com.example.todojpa.dto.comment.req.DeleteCommentRequestDto;
import com.example.todojpa.dto.comment.req.UpdateCommentRequestDto;
import com.example.todojpa.dto.comment.res.CommentResponseDto;
import com.example.todojpa.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos/{todoId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(
            @PathVariable Long todoId,
            @RequestBody CreateCommentRequestDto requestDto,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("username") : null;

        if (username == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        CommentResponseDto commentResponseDto = commentService.save(requestDto.getContent(), todoId, username);
        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable Long todoId) {
        List<CommentResponseDto> comments = commentService.findByTodoId(todoId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @PathVariable Long id,
            @PathVariable Long commentId,
            @RequestBody UpdateCommentRequestDto requestDto,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("username") : null;

        if (username == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        CommentResponseDto updatedCommentResponseDto = commentService.updateComment(commentId, requestDto, username);
        return new ResponseEntity<>(updatedCommentResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long todoId,
            @PathVariable Long commentId,
            @RequestBody DeleteCommentRequestDto requestDto,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("username") : null;

        if (username == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        commentService.deleteComment(commentId, requestDto.getPassword());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
