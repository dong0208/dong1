package com.kaishengit.web.task;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.AjaxResult;
import com.kaishengit.web.BaseServlet;
import com.kaishengit.web.service.TaskService;
@WebServlet("/task/add")
public class TaskAddServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;

	TaskService service = new TaskService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		forward("task/add",req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String taskName = req.getParameter("taskName");
		String finishTime = req.getParameter("finishTime");
		int accountId = getCurrAccount(req).getId();
		try {
			service.saveTask(taskName, finishTime, accountId);
			AjaxResult result = AjaxResult.success();
			sendJson(result,resp);
		} catch(ServiceException e) {
			AjaxResult result = AjaxResult.error(e.getMessage());
			sendJson(result,resp);
		}
	}
	
}
