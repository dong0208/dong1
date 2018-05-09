package com.kaishengit.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import com.kaishengit.dao.AccountDao;
import com.kaishengit.dao.AccountDeptDao;
import com.kaishengit.entity.Account;
import com.kaishengit.entity.AccountDept;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Config;
import com.kaishengit.util.EhcacheUtil;
import com.kaishengit.util.Page;

public class AccountService {
	EhcacheUtil cacheUtil = new EhcacheUtil();
	AccountDao accDao = new AccountDao();
	AccountDeptDao accountDeptDao = new AccountDeptDao();
	/**
	 * @param username 用户名
	 * @param password 密码
	 * @return account对象
	 */
	public Account login(String username, String password) {
		Account account = accDao.findByMobile(username);
		String md5Password = DigestUtils.md5Hex(password + Config.get("user.password.salt"));
		
		if(account != null && md5Password.equals(account.getPassword())) {
			 return account;
		} else {
			throw new ServiceException("用户名或者密码错误");
		}
	}
	
	
	/**
	 * 保存员工信息和部门员工关联关系信息
	 * @param userName 用户名
	 * @param password 密码(明文)
	 * @param mobile 手机号码
	 * @param deptId 部门id数组 
	 * @throws ServiceException  当手机号码已经存在的时候抛出该异常
	 */
	public void saveAccount(String userName, String password, String mobile, String[] deptIds) 
		throws ServiceException{
		
		// 校验手机号码是否已存在
		Account account = accDao.findByMobile(mobile);
		if(account != null) {
			throw new ServiceException("该电话号码已经存在");
		}
		
		// 新增account数据
		account = new Account();
		account.setUsername(userName);
		account.setMobile(mobile);
		String md5Password = DigestUtils.md5Hex(password + Config.get("user.password.salt"));
		account.setPassword(md5Password);
		account.setUpdateTime(new Timestamp(System.currentTimeMillis())); // 日期 + 时间
		
		// 调用save方法并获得自动生成的id
		int accountId = accDao.save(account);
		
		
		List<AccountDept> accountDeptLists = new ArrayList<>();
		for(String  deptId: deptIds) {
			AccountDept accountDept = new AccountDept();
			accountDept.setAccountId(accountId);
			accountDept.setDeptId(Integer.parseInt(deptId));
			accountDeptLists.add(accountDept); // 2--3  2--5
		}
		accountDeptDao.save(accountDeptLists);
		
		
	}


	/**
	 * 通过部门id和页码获取对应的page对象
	 * @param deptId
	 * @param pageNo
	 * @return
	 */
	public Page<Account> findAccountByPage(String deptId, int pageNo) {
		int count = accDao.count(deptId);
		Page<Account> page = new Page<>(count, pageNo);
		List<Account> accountList = accDao.findByPage(deptId,page.getStart(),page.getPageSize());
		page.setItems(accountList);
		return page;
	}


	/**
	 * 获得所有员工列表
	 * @return
	 */
	public List<Account> findAllAccount() {
		return accDao.findAll();
	}
	public Account findById(int accountId) {
		Account acc = (Account) cacheUtil.getCache("account", accountId);
		if(acc == null) {
			acc = accDao.findById(accountId);
			cacheUtil.setCache("account", accountId, acc);
		}
		return acc;
	}
	
	public void delById(int accountId) {
		accDao.findById(accountId);
//		cacheUtil.removeAll("account");
		cacheUtil.removeByKey("account","accountList");
		cacheUtil.removeByKey("account",accountId);
	}


	public void findPassword(int accountId) {
		String password = accDao.findByPassword(accountId);
		
	}


	public void edit(int id, String oldPassword, String newPassword, String mobile) {
		Account acc = accDao.findById(id);
		String md5Password = DigestUtils.md5Hex(newPassword + Config.get("user.password.salt"));
		acc.setPassword(md5Password);
		acc.setMobile(mobile);
		
		accDao.update(acc);
		
	}

}
