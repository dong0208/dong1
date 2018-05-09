package com.kaishengit.service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.dao.CustomerDao;
import com.kaishengit.dao.SaleDao;
import com.kaishengit.dao.SaleRecordDao;
import com.kaishengit.entity.Customer;
import com.kaishengit.entity.SaleChance;
import com.kaishengit.entity.SaleChanceRecord;
import com.kaishengit.exception.ForbiddenException;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Config;
import com.kaishengit.util.Page;

public class SaleService {

	CustomerDao custDao = new CustomerDao();
	SaleDao saleDao = new SaleDao();
	SaleRecordDao saleRecordDao = new SaleRecordDao();
	/**
	 * 获得销售机会的进度列表
	 * @return
	 */
	public List<String> findAllProcess() {
		String processString = Config.get("sales.progress");
		String[] processArray = processString.split(",");
		return Arrays.asList(processArray);
	}

	/**
	 * 获得当前登录员工的所有客户列表
	 * @param accountId
	 * @return
	 */
	public List<Customer> findAllCustomersByAccountId(int accountId) {
		return custDao.findByAccountId(accountId);
	}

	public void saveSaleChane(SaleChance saleChance) {
		// 修改customer表中last_concat_time
		Customer cust = custDao.findById(saleChance.getCustId());
		cust.setLastConcatTime(new Timestamp(System.currentTimeMillis()));
		custDao.update(cust);
		
		// 保存销售机会
		saleChance.setLastTime(new Timestamp(System.currentTimeMillis()));
		int saveId = saleDao.save(saleChance);
		
		// 如果填写了content，那么需要在sale_chance_record表中添加记录
		if(StringUtils.isNotEmpty(saleChance.getContent())) {
			SaleChanceRecord record = new SaleChanceRecord();
			record.setContent(saleChance.getContent());
			record.setSaleId(saveId);
			saleRecordDao.save(record);
		}
		
	}

	/**
	 * 根据accountId获得对应员工的销售机会列表的page对象
	 * @param pageNo 页码
	 * @param accountId 员工id
	 * @return page
	 */
	public Page<SaleChance> findSaleListByPage(int pageNo, int accountId) {
		// 获得总条数
		int count = saleDao.countByAccountId(accountId);
		Page<SaleChance> page = new Page<>(count, pageNo);
		List<SaleChance> saleChanceList = saleDao.findListByAccountIdAndPage(accountId,page.getStart(), page.getPageSize());
		page.setItems(saleChanceList);
		return page;
	}

	/**
	 * 根据id查找saleChance对象
	 * @param saleId
	 * @return
	 */
	public SaleChance findSaleChanceById(String saleId,int accountId) {
		
		SaleChance saleChance = checkSaleChance(saleId,accountId);
		
		return saleChance;
	}

	/**
	 * 1.判断saleid是否为纯数字
	 * 2.判断seleid是否合法，是否存在对应的saleChance对象
	 * 3.当前登录员工是否有查看该销售机会详情的权限
	 * @param saleId
	 * @return
	 */
	private SaleChance checkSaleChance(String saleId,int accountId) {
		if(!StringUtils.isNumeric(saleId)) {
			throw new ServiceException("参数异常");
		}
		
		SaleChance saleChance = saleDao.findById(Integer.parseInt(saleId));
		if(saleChance == null) {
			throw new ServiceException("参数异常");
		}
		
		if(saleChance.getAccountId() == accountId) {
			return saleChance;
		} else {
			throw new ForbiddenException("拒绝访问");
		}
		
	}

	/**
	 * 根据saleId查找跟进记录列表
	 * @param saleId
	 * @return
	 */
	public List<SaleChanceRecord> findRecordListBySaleId(String saleId) {
		return saleRecordDao.findListBySaleId(saleId);
	}

	/**
	 * 新增跟进记录
	 * @param record
	 * @param accountId
	 */
	public void addRecord(SaleChanceRecord record, int accountId) {
		// 新增跟进记录
		saleRecordDao.save(record);
		// 更新sale_chance表中对应销售机会的last_time
		SaleChance chance = saleDao.findById(record.getSaleId());
		Timestamp now = new Timestamp(System.currentTimeMillis());
		chance.setLastTime(now);
		
		saleDao.update(chance);
		// 更新customer表中客户的最后跟进时间 last_concat_time
		Customer cust = chance.getCustomer(); //custDao.findById(chance.getCustId());
		cust.setLastConcatTime(now);
		cust.setUpdateTime(now);
		custDao.update(cust);
		
	}

	/**
	 * 更新销售机会的进度
	 * @param saleId
	 * @param process
	 * @param accountId
	 */
	public void updateSaleChaneProcess(String saleId, String process, int accountId) {
		// 更新sale_chance的process字段
		SaleChance chance = saleDao.findById(Integer.parseInt(saleId));
		chance.setProcess(process);
		Timestamp now = new Timestamp(System.currentTimeMillis());
		chance.setLastTime(now);
		
		saleDao.update(chance);
		// 新增跟进记录
		SaleChanceRecord record = new SaleChanceRecord();
		record.setSaleId(Integer.parseInt(saleId));
		record.setContent("修改当前进度为[ " + process + " ]");
		saleRecordDao.save(record);
		
		// 修改customer的last_concat_time
		Customer cust = custDao.findById(chance.getCustId());
		cust.setLastConcatTime(now);
		custDao.update(cust);
	}

	/**
	 * 通过saleId删除销售机会
	 * @param saleId
	 * @param accountId
	 */
	public void delBySaleId(String saleId, int accountId) {
		SaleChance chance = checkSaleChance(saleId, accountId);
		// 删除销售机会对应的销售机会记录列表
		saleRecordDao.delBySaleId(chance.getId());
		// 删除销售机会
		saleDao.delById(chance.getId());
		
	}

	/**
	 * 查询客户的销售机会
	 * @param id
	 * @return
	 */
	public List<SaleChance> findSaleChanceListByCustId(int custId) {
		return saleDao.findListByCustId(custId);
	}


}
