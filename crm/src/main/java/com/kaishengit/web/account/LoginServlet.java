package com.kaishengit.web.account;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Account;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.AccountService;
import com.kaishengit.web.BaseServlet;

@WebServlet("/login")
public class LoginServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();
		for(Cookie cookie : cookies) {
			if("username".equals(cookie.getName())) {
				req.setAttribute("username", cookie.getValue());
			}
		}
		forward("account/login", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String remember = req.getParameter("remember");
		
		AccountService service = new AccountService();
		Map<String,Object> res = new HashMap<>();
		try { 
			Account account = service.login(username, password);
			
			HttpSession session = req.getSession();
			session.setAttribute("account", account);
			
			if(StringUtils.isNotEmpty(remember)) {
				Cookie cookie = new Cookie("username",username);
				cookie.setDomain("localhost");
				cookie.setPath("/");
				cookie.setMaxAge(60 * 60 * 24 * 30);
				cookie.setHttpOnly(true);
				
				resp.addCookie(cookie);
			} else {
				Cookie[] cookies = req.getCookies();
				for(Cookie cookie : cookies) {
					if("username".equals(cookie.getName())) {
						cookie.setDomain("localhost");
						cookie.setPath("/");
						cookie.setMaxAge(0);
						
						resp.addCookie(cookie);
					}
				}
			}
			
			res.put("state", "success");
			sendJson(res,resp);
			
		} catch(ServiceException e) {
			res.put("state", "error");
			res.put("message", e.getMessage());
			sendJson(res,resp);
		}
		
	}
	
	
}
