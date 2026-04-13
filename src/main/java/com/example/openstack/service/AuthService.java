package com.example.openstack.service;

import com.example.openstack.domain.Member;
import com.example.openstack.repository.AuthRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthRepository authRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public void signup(String username, String password, String name) {
        validateSignup(username, password, name);

        if (authRepository.existsByUsername(username)) {
            throw new RuntimeException("이미 사용 중인 아이디입니다.");
        }

        Member member = new Member(username, passwordEncoder.encode(password), name);
        authRepository.save(member);
    }

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

    public Member login(String username, String password) {
        Member member = authRepository.findByUsername(username);
        if (member == null) {
            return null;
        }

        String storedPassword = member.getPassword();

        // Legacy plain text support: allow one-time login and upgrade to BCrypt.
        if (!storedPassword.startsWith("$2a$") && !storedPassword.startsWith("$2b$")) {
            if (!storedPassword.equals(password)) {
                return null;
            }
            Member upgraded = new Member(member.getUsername(), passwordEncoder.encode(password), member.getName());
            authRepository.updatePassword(upgraded.getUsername(), upgraded.getPassword());
            member.setPassword(upgraded.getPassword());
            return member;
        }

        return passwordEncoder.matches(password, storedPassword) ? member : null;
    }
}
