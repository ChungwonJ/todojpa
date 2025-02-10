package com.example.todojpa.controller;

import com.example.todojpa.dto.user.req.SignInRequestDto;
import com.example.todojpa.dto.user.req.SignUpRequestDto;
import com.example.todojpa.dto.user.req.UpdatePasswordRequestDto;
import com.example.todojpa.dto.user.res.SignInResponseDto;
import com.example.todojpa.dto.user.res.SignUpResponseDto;
import com.example.todojpa.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private boolean userChecked(HttpServletRequest request, String todoUsername) {
        HttpSession session = request.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("username") : null;

        return username != null && username.equals(todoUsername);
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto requestDto) {

        SignUpResponseDto signUpResponseDto =
                userService.signUp(
                        requestDto.getUsername(),
                        requestDto.getPassword(),
                        requestDto.getEmail()
                );

        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SignInResponseDto> findById(@PathVariable Long id,HttpServletRequest request) {

        SignInResponseDto signInResponseDto = userService.findById(id);

        if (!userChecked(request, signInResponseDto.getUsername())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(signInResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(
            @PathVariable Long id,
            @RequestBody UpdatePasswordRequestDto requestDto,
            HttpServletRequest request
    ) {
        SignInResponseDto signInResponseDto = userService.findById(id);

        if (!userChecked(request, signInResponseDto.getUsername())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        userService.updatePassword(id, requestDto.getOldPassword(), requestDto.getNewPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @RequestBody Map<String, String> requestBody,
            HttpServletRequest request
    ) {
        String password = requestBody.get("password");
        SignInResponseDto signInResponseDto = userService.findById(id);

        if (!userChecked(request, signInResponseDto.getUsername())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        userService.delete(id, password);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponseDto> signIn(
            @RequestBody SignInRequestDto requestDto,
            HttpServletRequest request
    ) {
        SignInResponseDto signInResponseDto = userService.login(
                requestDto.getEmail(),
                requestDto.getPassword()
        );

        HttpSession session = request.getSession();
        session.setAttribute("username", signInResponseDto.getUsername());

        return new ResponseEntity<>(signInResponseDto, HttpStatus.OK);
    }

    @GetMapping("/session-info")
    public ResponseEntity<String> getSessionInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String username = (String) session.getAttribute("username");
            return ResponseEntity.ok("현재 사용자: " + username);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("세션이 존재하지 않습니다.");
        }
    }
}
