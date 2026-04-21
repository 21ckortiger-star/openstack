package com.example.openstack.repository;

import com.example.openstack.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Member> rowMapper = (rs, rowNum) -> {
        Member m = new Member();
        // 1. setMemberId 대신 setUsername을 사용하세요!
        m.setUsername(rs.getString("username"));
        m.setPassword(rs.getString("password"));
        m.setName(rs.getString("name"));
        return m;
    };

    public Optional<Member> findByUsername(String username) {
        String sql = "select username, password, name from member where username = ?";
        return jdbcTemplate.query(sql, rowMapper, username).stream().findFirst();
    }

    public void save(Member member) {
        String sql = "insert into member(username, password, name) values(?, ?, ?)";
        // 2. getMemberId 대신 getUsername을 사용하세요!
        jdbcTemplate.update(sql, member.getUsername(), member.getPassword(), member.getName());
    }

    public boolean existsByUsername(String username) {
        String sql = "select count(*) from member where username = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username);
        return count != null && count > 0;
    }
}