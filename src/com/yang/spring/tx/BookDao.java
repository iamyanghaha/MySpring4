package com.yang.spring.tx;

public interface BookDao {
	/**
	 * 传入id，查询价格
	 */
	int findBookPriceByBookName(int id);
	
	/**
	 * 更新库存
	 * 传入id增减图书数量
	 */
	void updateBookStock(int id);
	
	/**
	 * @param userName
	 * @param price
	 * 		更新用户余额
	 */
	void updateUserMoney(String userName , int price);
	
	
}
