package com.kaishengit.web.chart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.web.BaseServlet;

@WebServlet("/charts/demo")
public class ChartsDemoServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		forward("charts/static",req,resp);
	}
	
}
