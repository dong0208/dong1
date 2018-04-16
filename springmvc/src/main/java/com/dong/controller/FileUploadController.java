package com.dong.controller;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@Controller
public class FileUploadController {
    @GetMapping("/upload")
    public String FileUpload(@RequestHeader(name = "User-Agent")String userAgent, HttpServletRequest request,
                             HttpServletResponse response, HttpSession session){
        System.out.println("User-Agent"+userAgent);
        Cookie cookie = new Cookie("userAgent","svip");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60 * 24);
        cookie.setDomain("localhost");
        response.addCookie(cookie);
        return "upload";
    }
   @PostMapping("/upload")
    public String FileUpload(@CookieValue(name = "userAgent") String userAgent, String name, MultipartFile photo, RedirectAttributes redirectAttributes)throws IOException{
       System.out.println("userAgent:"+userAgent);
        if(!photo.isEmpty()){
            System.out.println("表单中元素的名字："+photo.getName());
            System.out.println("原始文件名称："+photo.getOriginalFilename());
            System.out.println("ContentType："+photo.getContentType());
            System.out.println("文件大小："+ FileUtils.byteCountToDisplaySize(photo.getSize()));
            //photo.transferTo(new File("G:/upload"+photo.getOriginalFilename()));
            InputStream in = photo.getInputStream();
            OutputStream out = new FileOutputStream("G:/upload/"+photo.getOriginalFilename());
            IOUtils.copy(in,out);
            out.flush();
            out.close();
            in.close();
        }else{
            System.out.println("请选择文件");
            redirectAttributes.addFlashAttribute("message","请选择文件");
            /*在重定向到目标对象时，可以可以通过该对象的addFlashAttribute（string key.Object）
            方法目标对象传值*/
        }

    return "redirect:/upload";
    }
    @ExceptionHandler(IOException.class)
    public String ioException(){
        return "error/500";
    }


}
