package com.kaisheng.dao;

import org.junit.Test;

import com.kaishengit.dao.AccountDao;
import com.kaishengit.entity.Account;

public class AccountDaoCacheTestCase {
	
	private AccountDao accDao = new AccountDao();
	
	@Test
	public void findByIdCacheTest() {
		Account acc = accDao.findById(15);
		Account acc1 = accDao.findById(15);
		
	
	}
	
	
}
