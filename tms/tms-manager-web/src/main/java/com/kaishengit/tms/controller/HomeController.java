package com.kaishengit.tms.controller;

import com.kaishengit.tms.service.AccountService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * 首页及登录、登出的控制器
 * @author 杨冬冬
 */
@Controller
public class HomeController {

    @Autowired
    private AccountService accountService;

    /**
     * 系统登录页面
     * @return
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * 系统登录
     * @return
     */
    @PostMapping("/")
    public String login(String accountMobile,
                        String password,
                        String rememberMe,
                        HttpServletRequest request,
                        RedirectAttributes redirectAttributes) {

        // 创建Subject对象
        Subject subject = SecurityUtils.getSubject();
        // 根据账号和密码进行登录
        String requestIp = request.getRemoteAddr();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(accountMobile,DigestUtils.md5Hex(password),rememberMe!=null,requestIp);

        try {
            subject.login(usernamePasswordToken);

            //登录后跳转目标的判断
            SavedRequest savedRequest = WebUtils.getSavedRequest(request);
            String url = "/home";
            if(savedRequest != null) {
                url = savedRequest.getRequestUrl();
            }

            return "redirect:"+url;
        } catch (UnknownAccountException | IncorrectCredentialsException ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("message","账号或密码错误");
        } catch (LockedAccountException ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("message","账号被锁定");
        } catch (AuthenticationException ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("message","账号或密码错误");
        }
        return "redirect:/";
    }

    /**
     * 登录后的首页
     * @return
     */
    @GetMapping("/home")
    public String home() {
        return "home";
    }
    @GetMapping("/401")
    public String unauthorizedUrl (){
        return "error/401";
    }

}
