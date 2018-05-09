package com.kaishengit.web.sale;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Customer;
import com.kaishengit.entity.SaleChance;
import com.kaishengit.service.SaleService;
import com.kaishengit.util.AjaxResult;
import com.kaishengit.web.BaseServlet;

@WebServlet("/sale/add")
public class SaleChanceAddServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	SaleService saleService = new SaleService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int accountId = getCurrAccount(req).getId();
		
		List<String> process = saleService.findAllProcess();
		List<Customer> customers = saleService.findAllCustomersByAccountId(accountId);
		req.setAttribute("process", process);
		req.setAttribute("customerList", customers);
		forward("sale/add", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String saleName = req.getParameter("salename");
		String custId = req.getParameter("custId");
		String worth = req.getParameter("worth");
		String process = req.getParameter("process");
		String content = req.getParameter("content");
		
		int accountId = getCurrAccount(req).getId();
		
		
		SaleChance saleChance = new SaleChance(saleName, Integer.parseInt(custId), Float.parseFloat(worth), process, content, accountId);
		saleService.saveSaleChane(saleChance);
		AjaxResult result = AjaxResult.success();
		sendJson(result,resp);
	}
	
}
