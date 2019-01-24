package com.wonders.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void add(String name, Integer age) {
		String sql = "INSERT INTO users(NAME, age) VALUES(?,?);";
		int updateResult = jdbcTemplate.update(sql, name, age);
		System.out.println("updateResult:" + updateResult);
	}

}
