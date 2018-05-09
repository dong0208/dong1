package com.kaisheng.dao;

import static org.junit.Assert.assertNotNull;

import java.sql.Timestamp;

import org.junit.After;
import org.junit.Test;

import com.kaishengit.dao.AccountDao;
import com.kaishengit.entity.Account;

public class AccountDaoTest {
	Account acc = new Account();
	AccountDao accDao = new AccountDao();
	@Test
	public void saveTest(){
		acc.setPassword("000000");
		acc.setUsername("李白");
		acc.setMobile("156");
		acc.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		accDao.save(acc);
	}
	@Test
	public void findByTest(){
		int accountId = 10;
		Account acc = accDao.findById(accountId);
		//断言机制
		assertNotNull(acc);
		
	}
	@After
	public void  after(){
		System.out.println("测试已通过");
	}
}
