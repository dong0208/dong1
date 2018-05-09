package com.kaishengit.web.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Account;
import com.kaishengit.service.AccountService;
import com.kaishengit.util.AjaxResult;
import com.kaishengit.util.Page;
import com.kaishengit.web.BaseServlet;

@WebServlet("/account/list.json")
public class AccountListJsonServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	AccountService accountService = new AccountService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String deptId = req.getParameter("deptId");
		String p = req.getParameter("p");
		
		int pageNo = 1;
		if(StringUtils.isNumeric(p)) {
			pageNo = Integer.parseInt(p);
		}
		Page<Account> pages = accountService.findAccountByPage(deptId, pageNo);
		AjaxResult result = AjaxResult.success(pages);
		sendJson(result,resp);
	}
	
}
