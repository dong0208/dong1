package com.kaishengit.web.dept;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.DeptService;
import com.kaishengit.util.AjaxResult;
import com.kaishengit.web.BaseServlet;

@WebServlet("/dept/add")
public class DeptAddServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;

	DeptService deptService = new DeptService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String deptName = req.getParameter("deptName");
		try {
			deptService.addDept(deptName);
			// 封装返回值对象
			AjaxResult result = AjaxResult.success();
			sendJson(result, resp);
		} catch(ServiceException e) {
			AjaxResult result = AjaxResult.error(e.getMessage());
			sendJson(result, resp);
		}
	
	}
	
}
