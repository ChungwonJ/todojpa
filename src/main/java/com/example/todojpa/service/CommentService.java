package com.example.todojpa.service;

import com.example.todojpa.config.PasswordEncoder;
import com.example.todojpa.dto.comment.req.UpdateCommentRequestDto;
import com.example.todojpa.dto.comment.res.CommentResponseDto;
import com.example.todojpa.entity.Comment;
import com.example.todojpa.entity.Todo;
import com.example.todojpa.entity.User;
import com.example.todojpa.repository.CommentRepository;
import com.example.todojpa.repository.TodoRepository;
import com.example.todojpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;
    private final PasswordEncoder passwordEncoder;

    public CommentResponseDto save(String content, Long todoId, String username) {
        User user = userRepository.findUserByUsernameOrElseThrow(username);
        Todo todo = todoRepository.findByIdOrElseThrow(todoId);

        Comment comment = new Comment(content);
        comment.setUser(user);
        comment.setTodo(todo);
        commentRepository.save(comment);

        return new CommentResponseDto(comment.getId(), username, comment.getContent());
    }

    public List<CommentResponseDto> findByTodoId(Long todoId) {
        List<Comment> comments = commentRepository.findByTodoId(todoId);
        return comments.stream()
                .map(comment -> new CommentResponseDto(comment.getId(), comment.getContent(), comment.getUser().getUsername()))
                .collect(Collectors.toList());
    }

    public CommentResponseDto updateComment(Long commentId, UpdateCommentRequestDto requestDto, String username) {
        Comment comment = commentRepository.findByIdOrElseThrow(commentId);

        if (!passwordEncoder.matches(requestDto.getPassword(), comment.getUser().getPassword())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "잘못된 비밀번호입니다.");
        }

        comment.setContent(requestDto.getContent());
        commentRepository.save(comment);

        return new CommentResponseDto(comment.getId(), username, comment.getContent());
    }

    public void deleteComment(Long commentId, String password) {
        Comment comment = commentRepository.findByIdOrElseThrow(commentId);

        if (!passwordEncoder.matches(password, comment.getUser().getPassword())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "잘못된 비밀번호입니다.");
        }

        commentRepository.delete(comment);
    }
}
