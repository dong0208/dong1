package com.kaishengit.web.dept;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Dept;
import com.kaishengit.service.DeptService;
import com.kaishengit.web.BaseServlet;

@WebServlet("/dept/list")
public class DeptListServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;

	DeptService deptService = new DeptService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Dept> deptList = deptService.findAllDeptList();
		sendJson(deptList, resp);
		
	}
	
}
