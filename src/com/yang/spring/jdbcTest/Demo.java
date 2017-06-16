package com.yang.spring.jdbcTest;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class Demo {
	private ApplicationContext apc;
	private JdbcTemplate jt;
	private NamedParameterJdbcTemplate namedjt;
	
	{
		apc = new ClassPathXmlApplicationContext("applicationContext.xml");
		 jt= (JdbcTemplate) apc.getBean("jdbcTemplate");
		 namedjt = apc.getBean(NamedParameterJdbcTemplate.class);
	}
	
	/*
	 * 测试NamedParameterJdbcTemplate
	 * 		相比，named可以为参数设置名字
	 */
	@Test
	public void testNamedParameterJdbcTemplate(){
		String sql = "insert into user2 values(:name, :age, :gender)";
		User u = new User("我是谁", 88, "未知");
		SqlParameterSource sps = new BeanPropertySqlParameterSource(u);

		namedjt.update(sql, sps);
		
//		Map<String , Object> paramMap = new HashMap<>();
//		paramMap.put("name", "张飞飞");
//		paramMap.put("age", 22);
//		paramMap.put("gender", "男");
//		
//		namedjt.update(sql, paramMap);
	}
	
	@Test
	public void testQueryForList(){
		String sql = "select * from user2 where age > ?";
		
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class); 
		List<User> u = jt.query(sql, rowMapper, 10);
		System.out.println(u);
	}

	@Test
	public void testQuery2(){
		String sql = "select count(name) from user2";
		Long u = jt.queryForObject(sql, Long.class);
		System.out.println(u);
	}

	
	//单个查询
	@Test
	public void testQuery1(){
		String sql = "select * from user2 where age=21";
		
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class); 
		User u = jt.queryForObject(sql, rowMapper);
		System.out.println(u);
	}

	
	/**
	 * 执行批量更新（update或者insert）
	 */
	@Test
	public void testBatchUpdate(){
		String sql = "INSERT INTO user2 VALUES (?, ?, ?)";
		List<Object[]> batchArgs = new ArrayList<>();
		
		batchArgs.add(new Object[]{"陈帅帅", 21, "男"});
		batchArgs.add(new Object[]{"啊哈哈", 20, "女"});
		batchArgs.add(new Object[]{"李帅帅", 23, "未知"});
		
		jt.batchUpdate(sql, batchArgs);
	}
	
	@Test
	public void testUpdate(){
		String sql = "UPDATE user2 set age=? WHERE name=? ";
		jt.update(sql, 6, "ed");

	}
	
	@Test
	public void testJdbcTemplate(){		
		System.out.println(jt);
	}
	
	@Test
	public void testDataSource(){
		DataSource ds = (DataSource) apc.getBean("dataSource");
		System.out.println(ds);
		
	}
}
