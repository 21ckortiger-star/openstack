package com.example.lms.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public void validateSignup(String username, String password, String name) {
        if (username == null || username.isBlank()) {
            throw new RuntimeException("아이디는 필수입니다.");
        }
        if (password == null || password.length() < 4) {
            throw new RuntimeException("비밀번호는 4자 이상이어야 합니다.");
        }
        if (name == null || name.isBlank()) {
            throw new RuntimeException("이름은 필수입니다.");
        }
    }

    public boolean login(String username, String password) {
        return "admin".equals(username) && "1234".equals(password);
    }
}