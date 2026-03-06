package com.example.lms.repository;

import com.example.lms.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AuthRepository {

    private final JdbcTemplate jdbcTemplate;

    public AuthRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Member> rowMapper = (rs, rowNum) -> {
        Member m = new Member();
        m.setUsername(rs.getString("username"));
        m.setPassword(rs.getString("password"));
        m.setName(rs.getString("name"));
        return m;
    };

    public void save(Member member) {
        String sql = "insert into member(username, password, name) values(?, ?, ?)";
        jdbcTemplate.update(sql, member.getUsername(), member.getPassword(), member.getName());
    }

    public Member findByUsername(String username) {
        String sql = "select username, password, name from member where username = ?";
        var result = jdbcTemplate.query(sql, rowMapper, username);
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    public boolean existsByUsername(String username) {
        String sql = "select count(*) from member where username = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username);
        return count != null && count > 0;
    }
}