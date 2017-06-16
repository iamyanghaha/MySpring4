package com.yang.spring.tx;

public interface BookService {
	
	/**
	 * 购买图书。
	 * 	（依赖bookDao）
	 * 	1.传入id查询图书价格
	 *  2.通过id使图书库存减一
	 *  3.减少用户剩余钱数
	 */
	void purchase(String userName, int id);
}
