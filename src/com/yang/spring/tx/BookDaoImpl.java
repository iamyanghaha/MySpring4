package com.yang.spring.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("bookDao")
public class BookDaoImpl implements BookDao {
	
	@Autowired
	private JdbcTemplate jt;
	
	@Override
	public int findBookPriceByBookName(int id) {
	
		String sql = "SELECT price FROM bookPrice WHERE id = ?";
		return jt.queryForObject(sql, Integer.class, id);
	}

	@Override
	public void updateBookStock(int id) {
		String sql2 = "select num from bookNum where id = ?";
		int stock = jt.queryForObject(sql2, Integer.class , id);
		
		if(stock == 0){
			throw new BookAccountException("本书["+ id +"]库存不足!");
		}
		String sql = "UPDATE bookNum SET num = num - 1 WHERE id = ?";
		jt.update(sql, id);
	}

	@Override
	public void updateUserMoney(String userName, int price) {
		// TODO Auto-generated method stub
		String sql2 = "select money from bookuser where username = ?";
		int stock = jt.queryForObject(sql2, Integer.class , userName);
		
		if(stock < price){
			throw new BookAccountException("余额不足!");
		}
		
		
		String sql = "update bookuser set money = money - ?";
		jt.update(sql, price);
	}
	
}
