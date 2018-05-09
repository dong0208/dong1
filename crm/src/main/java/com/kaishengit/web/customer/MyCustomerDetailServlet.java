package com.kaishengit.web.customer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Account;
import com.kaishengit.entity.Customer;
import com.kaishengit.entity.SaleChance;
import com.kaishengit.exception.ForbiddenException;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.AccountService;
import com.kaishengit.service.CustomerService;
import com.kaishengit.service.SaleService;
import com.kaishengit.web.BaseServlet;
@WebServlet("/customer/my/detail")
public class MyCustomerDetailServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	CustomerService custService = new CustomerService();
	AccountService accService= new AccountService();
	SaleService saleService = new SaleService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String custId = req.getParameter("id");
		int accountId = getCurrAccount(req).getId();
		
		try {
			Customer cust = custService.findCustomerById(custId,accountId);
			List<Account> accList = accService.findAllAccount();
			// 把当前客户的销售机会列表显示在页面的跟进机会模块中
			List<SaleChance> chanceList = saleService.findSaleChanceListByCustId(cust.getId());
			
			req.setAttribute("customer", cust);
			req.setAttribute("accountList", accList);
			req.setAttribute("chanceList", chanceList);
			forward("customer/mydetail", req, resp);
		} catch(ServiceException e) {
			resp.sendError(404,e.getMessage());
		} catch(ForbiddenException e) {
			resp.sendError(403,e.getMessage());
		}
		
		
	
	}
	
}
