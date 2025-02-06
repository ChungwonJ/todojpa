package com.example.todojpa.service;

import com.example.todojpa.dto.user.res.SignInResponseDto;
import com.example.todojpa.dto.user.res.SignUpResponseDto;
import com.example.todojpa.entity.User;
import com.example.todojpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        if (user != null && user.getPassword().equals(password)) { // 평문 비교
            return new SignInResponseDto(user.getId(), user.getUsername());
        }
        throw new RuntimeException("Invalid email or password");
    }
}
