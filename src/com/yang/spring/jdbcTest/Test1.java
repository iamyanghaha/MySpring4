package com.yang.spring.jdbcTest;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class Test1 {
	
	private ApplicationContext apc;
	private JdbcTemplate jdbcTemplate;
	
	{
		apc = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) apc.getBean("jdbcTemplate");
	}
	
	@Test
	public void testUpdate(){
		String sql = "UPDATE user set age=? WHERE name=?";
		jdbcTemplate.update(sql, 88, "³ÂË§Ë§");
	}
	
	
	@Test
	public void fun1(){
		DataSource data = (DataSource) apc.getBean("dataSource");
		System.out.println(data);
		
	}
}
