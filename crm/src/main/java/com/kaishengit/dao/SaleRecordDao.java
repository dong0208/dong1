package com.kaishengit.dao;

import java.util.List;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.kaishengit.entity.SaleChanceRecord;
import com.kaishengit.util.DbHelp;

public class SaleRecordDao {

	public void save(SaleChanceRecord record) {
		String sql = "insert into sale_chance_record (content,sale_id) values (?,?)";
		DbHelp.executeUpdate(sql, record.getContent(), record.getSaleId());
	}

	public List<SaleChanceRecord> findListBySaleId(String saleId) {
		String sql = "select * from sale_chance_record where sale_id = ?";
		return DbHelp.query(sql, new BeanListHandler<>(SaleChanceRecord.class, new BasicRowProcessor(new GenerousBeanProcessor())), saleId);
	}

	public void delBySaleId(int saleId) {
		String sql = "delete from sale_chance_record where sale_id = ?";
		DbHelp.executeUpdate(sql, saleId);
	}

}
