package com.kaishengit.tms.controller;

import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.service.AccountService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private AccountService accountService;

    /**
     * ϵͳ��¼ҳ��
     * @return
     */
    @GetMapping("/")
    public String index() {
        Subject subject = SecurityUtils.getSubject();

        //�жϵ�ǰ�Ƿ����Ѿ���֤���˺ţ�����У����˳����˺�
        if(subject.isAuthenticated()) {
            subject.logout();
        }

        if(subject.isRemembered()) {
            //�����ǰΪ����ס��ͨ��rememberMe��֤������ֱ����ת����¼�ɹ�ҳ�� home
            return "redirect:/home";
        }


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
        UsernamePasswordToken usernamePasswordToken =
                new UsernamePasswordToken(accountMobile,DigestUtils.md5Hex(password),rememberMe!=null,requestIp);

        try {
            subject.login(usernamePasswordToken);

            if(subject.hasRole("finance") || subject.hasRole("store")) {
                //��¼����תĿ����ж�
                SavedRequest savedRequest = WebUtils.getSavedRequest(request);
                String url = "/home";
                if(savedRequest != null) {
                    url = savedRequest.getRequestUrl();
                }

                return "redirect:"+url;
            } else {
                logger.info("{} û��Ȩ�޵�¼��ϵͳ",accountMobile);
                redirectAttributes.addFlashAttribute("message","û�е�¼��ϵͳ��Ȩ��");
            }
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

    /**
     * ��ȫ�˳�
     * @return
     */
    /*@GetMapping("/logout")
    public String logout(RedirectAttributes redirectAttributes) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        redirectAttributes.addFlashAttribute("message","���Ѱ�ȫ�˳�ϵͳ");
        return "redirect:/";
    }*/

    @GetMapping("/401")
    public String unauthorizedUrl() {
        return "error/401";
    }

}