package com.dong.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter{

   public boolean preHandler(HttpServletRequest request, HttpServletResponse response,
                            Object handler)throws Exception{
       //ÇëÇóµÄURL
       String url = request.getRequestURI();
       if (url.startsWith("/static/") || url.equals("/favicon,ico/")){
           return true;
       }
       if ("".equals(url) || "/".equals(url)){
           return true;
       }
       return true;
    }
}
