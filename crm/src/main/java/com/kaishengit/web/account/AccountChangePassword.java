package com.kaishengit.web.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Account;
import com.kaishengit.service.AccountService;
import com.kaishengit.util.AjaxResult;
import com.kaishengit.web.BaseServlet;
@WebServlet("/account/change/password")
public class AccountChangePassword extends BaseServlet{
	AccountService service = new AccountService();
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int accountId = getCurrAccount(req).getId();
		Account account = service.findById(accountId);
		req.setAttribute("account", account);
		forward("account/changePassword", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("accountId");
		String oldPassword = req.getParameter("oldPassword");
		String newPassword = req.getParameter("newPassword");
		String mobile = req.getParameter("mobile");
		
		service.edit(Integer.parseInt(id),oldPassword,newPassword,mobile);
		AjaxResult result = AjaxResult.success();
		sendJson(result, resp);
	
	}
}
