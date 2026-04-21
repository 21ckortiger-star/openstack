package com.example.openstack.service;

import com.example.openstack.domain.Member;
import com.example.openstack.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(String username, String password, String name) {

        String encodedPassword = passwordEncoder.encode(password);

        Member member = Member.builder()
                .username(username)
                .password(encodedPassword)
                .name(name)
                .build();

        authRepository.save(member);
    }

    public Member login(String username, String password) {

        Member member = authRepository.findByUsername(username)
                .orElse(null);

        if (member != null && passwordEncoder.matches(password, member.getPassword())) {
            return member;
        }

        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = authRepository.findByUsername(username) // 이름을 통일시켜요!
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없어요: " + username));

        return User.builder()
                .username(member.getUsername()) // ⬅️ 여기를 getUsername()으로 수정!
                .password(member.getPassword())
                .roles("USER")
                .build();

    }
}