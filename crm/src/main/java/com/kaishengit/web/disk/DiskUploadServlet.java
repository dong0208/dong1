package com.kaishengit.web.disk;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.kaishengit.service.DiskService;
import com.kaishengit.util.AjaxResult;
import com.kaishengit.web.BaseServlet;
@WebServlet("/disk/upload")
@MultipartConfig
public class DiskUploadServlet extends BaseServlet{
	DiskService service = new DiskService();
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		Part part = req.getPart("file");
		//获取文件上传的二进制流
		InputStream input = part.getInputStream();
		//获取文件上传的原始名称
		String name = req.getParameter("name");
		//获取文件上传的大小
		long fileSize = part.getSize();
		//从formData中获取pId
		String pid = req.getParameter("pid");
		String md5 = req.getParameter("fileMd5");
		System.out.println("md5:"+md5);
		
		try{
			//获取AccountId
			int accountId = getCurrAccount(req).getId();
			String saveName = UUID.randomUUID()+name.substring(name.lastIndexOf("."));
			//数据入库
			service.saveNewFile(saveName,name,fileSize,Integer.parseInt(pid),accountId,md5);
			//保存文件
			service.uploadFile(input,saveName);
			sendJson(AjaxResult.success(),resp);
		}catch(NullPointerException e){
			sendJson(AjaxResult.error("登录信息已过期，请重新登录"),resp);
		}
	}
	
	
}
