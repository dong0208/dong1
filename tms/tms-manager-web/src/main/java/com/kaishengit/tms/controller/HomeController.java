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
 * ��ҳ����¼���ǳ��Ŀ�����
 * @author ���
 */
@Controller
public class HomeController {

    @Autowired
    private AccountService accountService;

    /**
     * ϵͳ��¼ҳ��
     * @return
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * ϵͳ��¼
     * @return
     */
    @PostMapping("/")
    public String login(String accountMobile,
                        String password,
                        String rememberMe,
                        HttpServletRequest request,
                        RedirectAttributes redirectAttributes) {

        // ����Subject����
        Subject subject = SecurityUtils.getSubject();
        // �����˺ź�������е�¼
        String requestIp = request.getRemoteAddr();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(accountMobile,DigestUtils.md5Hex(password),rememberMe!=null,requestIp);

        try {
            subject.login(usernamePasswordToken);

            //��¼����תĿ����ж�
            SavedRequest savedRequest = WebUtils.getSavedRequest(request);
            String url = "/home";
            if(savedRequest != null) {
                url = savedRequest.getRequestUrl();
            }

            return "redirect:"+url;
        } catch (UnknownAccountException | IncorrectCredentialsException ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("message","�˺Ż��������");
        } catch (LockedAccountException ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("message","�˺ű�����");
        } catch (AuthenticationException ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("message","�˺Ż��������");
        }
        return "redirect:/";
    }

    /**
     * ��¼�����ҳ
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
