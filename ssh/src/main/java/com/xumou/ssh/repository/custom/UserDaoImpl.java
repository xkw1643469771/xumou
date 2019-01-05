package com.xumou.ssh.repository.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> selectUser() {
        return jdbcTemplate.queryForList("SELECT * FROM USER");
    }

}