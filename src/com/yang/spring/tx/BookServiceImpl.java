package com.yang.spring.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("bookService")
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookDao bookDao;
	
	@Transactional
	public void purchase(String userName, int id) {
	
		//1.传入id查询图书价格
		int price = bookDao.findBookPriceByBookName(id);
		
		//2.通过id使图书库存减一
		bookDao.updateBookStock(id);
		
		//3.减少用户剩余钱数
		bookDao.updateUserMoney(userName, price);
		
	}

}
