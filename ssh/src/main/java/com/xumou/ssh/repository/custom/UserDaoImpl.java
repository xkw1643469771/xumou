package com.xumou.ssh.repository.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.xumou.ssh.entity.User;

import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> selectUser() {
        return jdbcTemplate.queryForList("SELECT * FROM USER");
    }

	@Override
	public void insertUser(User user) {
		StringBuilder sb = new StringBuilder();
		sb.append(" INSERT INTO USER(ID, NAME, ID_CARD, AGE)");
		sb.append(" VALUES(HIBERNATE_SEQUENCE.NEXTVAL, ?, ?, ?)");
		jdbcTemplate.update(sb.toString(), user.getName(), user.getIdCard(), user.getAge());
	}

}