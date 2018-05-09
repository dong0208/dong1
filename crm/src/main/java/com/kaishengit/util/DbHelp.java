package com.kaishengit.util;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.kaishengit.exception.DataAccessException;

public class DbHelp {

	private static QueryRunner runner = new QueryRunner(ConnectionManager.getDataSource());

	/**
	 * 执行insert，用于需要获得新数据的id值的时候使用该方法，如果不需要请使用executeUpdate()方法 
	 */
	public static int executeInsert(String sql, Object... params) {
		try {
			System.out.println("SQL:" + sql);
			return runner.insert(sql, new ScalarHandler<Long>(),params).intValue(); // Long -- > int
		} catch (SQLException e) {
			throw new DataAccessException("ִ执行" + sql + "异常", e);
		} 
	}
	
	/**
	 * 执行insert delete update
	 */
	public static void executeUpdate(String sql, Object... params) {
		try {
			System.out.println("SQL:" + sql);
			runner.update(sql, params);
		} catch (SQLException e) {
			throw new DataAccessException("ִ执行" + sql + "异常", e);
		} 
	}

	public static <T> T query(String sql, ResultSetHandler<T> rsh, Object... params) {
		try {
			System.out.println("SQL:" + sql);
			return runner.query(sql, rsh, params);
		} catch (SQLException e) {
			throw new DataAccessException("ִ执行" + sql + "异常", e);
		} 
	}
}