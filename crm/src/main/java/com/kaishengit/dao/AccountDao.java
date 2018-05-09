package com.kaishengit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Account;
import com.kaishengit.entity.Customer;
import com.kaishengit.util.DbHelp;

public class AccountDao {
	private static Map<String, Account> cache = new HashMap<>();
	public Account findbyByName(String username) {
		String sql = "select * from t_account where username = ?";
		return DbHelp.query(sql, new BeanHandler<>(Account.class), username);
	}

	public Account findByMobile(String mobile) {
		String sql = "select * from t_Account where mobile = ?";
		return DbHelp.query(sql, new BeanHandler<>(Account.class, new BasicRowProcessor(new GenerousBeanProcessor())), mobile);
	}

	public int save(Account account) {
		String sql = "insert into t_account (username, password, mobile, update_time) values (?,?,?,?)";
		return DbHelp.executeInsert(sql, account.getUsername(),account.getPassword(), account.getMobile(),account.getUpdateTime());
	}

	public int count(String deptId) {
		if(StringUtils.isNotEmpty(deptId)) {
			String sql = "select count(*) from t_account a inner join t_account_dept ad on a.id = ad.account_id ";
			sql += "where ad.dept_id = ? ";
			return DbHelp.query(sql, new ScalarHandler<Long>(),deptId).intValue();
		} else {
			String sql = "select count(*) from t_account";
			return DbHelp.query(sql, new ScalarHandler<Long>()).intValue();
		}
		
	}

	public List<Account> findByPage(String deptId, int start, int pageSize) {
		String sql = "select a.id,a.username,a.mobile,d.deptname from t_Account a inner JOIN t_account_dept ad on a.id = ad.account_id inner join t_dept d on ad.dept_id = d.id ";
		/*List<Object> arrays = new ArrayList<>();
		if(StringUtils.isNotEmpty(deptId)) {
			sql += "where ad.dept_id = ? ";
			arrays.add(deptId);
		}
		
		sql += "order by a.id desc limit ?,?";
		arrays.add(start);
		arrays.add(pageSize);*/
		
		List<Object> arrays = new ArrayList<>();
		if(StringUtils.isNotEmpty(deptId)) {
			sql += "Inner join ("
					+ "select id from t_account "
					+ "inner join t_account_dept "
					+ "on t_account.id = t_account_dept.account_id "
					+ "where dept_id = ? limit ?,?) "
					+ "as temp on a.id = temp.id  ORDER BY a.id";
			arrays.add(deptId);
		} else {
			sql += "inner join (select id from t_account limit ?,? ) as temp on a.id = temp.id  ORDER BY a.id";
		}
		
		arrays.add(start);
		arrays.add(pageSize);
		
		return DbHelp.query(sql, new ResultSetHandler<List<Account>>() {
			List<Account> accountList = new ArrayList<Account>();
			@Override
			public List<Account> handle(ResultSet rs) throws SQLException {
				while(rs.next()) {
					// 封装account对象
					Account account = new Account();
					account.setId(rs.getInt("id"));
					account.setUsername(rs.getString("username"));
					account.setMobile(rs.getString("mobile"));
					
					Account acc = checkAccount(account);
					
					if(acc != null) {
						// 该员工数据已经被添加到List集合，更新deptname的值即可
						acc.setDeptName(acc.getDeptName() + " " + rs.getString("deptname"));
					} else {
						// 该员工数据未添加到List集合，封装deptName属性，将account'对象添加到List集合
						account.setDeptName(rs.getString("deptname"));
						accountList.add(account);
					}
					
					
				}
				return accountList;
			}
			
			private Account checkAccount(Account account){
				for(Account acc : accountList) {
					if(acc.equals(account)) {
						return acc;
					}
				}
				
				return null;
			}
			
		}, arrays.toArray());
	}

	public List<Account> findAll() {
		String sql = "select * from t_account";
		return DbHelp.query(sql, new BeanListHandler<>(Account.class,new BasicRowProcessor(new GenerousBeanProcessor())));
	}

	/*public Account findById(int accountId) {
		String sql = "select * from t_account where id = ?";
		return DbHelp.query(sql, new BeanHandler<>(Account.class,new BasicRowProcessor(new GenerousBeanProcessor())),accountId);
	}*/
	public Account findById(int accountId) {
	Account acc = cache.get("user"+accountId); // user1  user1002
	if(acc == null) {
		String sql = "select * from t_account where id = ?";
		acc = DbHelp.query(sql, new BeanHandler<>(Account.class,new BasicRowProcessor(new GenerousBeanProcessor())),accountId);
		// 存储到map集合
		cache.put("user" + accountId, acc);
	}
	return acc;
}

	public String findByPassword(int accountId) {
		String sql = "select * from t_account where id = ?";
		return null;
	}

	public void update(Account acc) {

		String sql = "update t_account set password=?,mobile=? where id=?";
		DbHelp.executeUpdate(sql,acc.getPassword(),acc.getMobile(),acc.getId() );
		
	}

}
