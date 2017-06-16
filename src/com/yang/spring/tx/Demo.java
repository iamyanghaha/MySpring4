package com.yang.spring.tx;

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
	private BookDaoImpl bookDao;
	
	{
		apc = new ClassPathXmlApplicationContext("applicationContext.xml");
		bookDao = (BookDaoImpl) apc.getBean("bookDao");
	}
	
	@Test
	public void testService(){
		BookService bs = (BookService) apc.getBean("bookService");
		bs.purchase("asd", 1);
	}
	
	
	@Test
	public void testFindBookPriceByBookName(){
		System.out.println(bookDao.findBookPriceByBookName(1));
		//bookDao.updateBookStock(1);
		//bookDao.updateUserMoney("asd", 11);
	}
}
