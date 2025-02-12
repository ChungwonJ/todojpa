package com.example.todojpa.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "comment")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "todo_id")
    private Todo todo;

    public Comment() {
    }

    public Comment(String content) {
        this.content = content;
    }

    public void setUser(User findUser) {
        this.user = findUser;
    }

    public void setTodo(Todo findTodo) {
        this.todo = findTodo;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
