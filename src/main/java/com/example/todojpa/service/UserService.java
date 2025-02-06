package com.example.todojpa.service;

import com.example.todojpa.dto.user.res.SignInResponseDto;
import com.example.todojpa.dto.user.res.SignUpResponseDto;
import com.example.todojpa.entity.User;
import com.example.todojpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public SignUpResponseDto signUp(String username, String password, String email) {
        User user = new User(username, password, email);

        User savedMember = userRepository.save(user);

        return new SignUpResponseDto(savedMember.getId(), savedMember.getUsername(), savedMember.getEmail());
    }

    public SignInResponseDto login(String email, String password) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }

        if (user.getPassword().equals(password)) {
            return new SignInResponseDto(user.getId(), user.getUsername());
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password incorrect");
        }
    }
}
