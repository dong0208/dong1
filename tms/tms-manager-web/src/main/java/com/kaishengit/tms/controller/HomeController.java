package com.kaishengit.tms.controller;

import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
                        HttpServletRequest request,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) {
        //��ȡ��¼��IP��ַ
        String requestIp = request.getRemoteAddr();
        try {
            Account account = accountService.login(accountMobile, password, requestIp);
            //����¼�ɹ��Ķ������session
            session.setAttribute("current_Account",account);
            //����homeҳ��
            return "redirect:/home";
        } catch (ServiceException ex) {
            redirectAttributes.addFlashAttribute("phone",accountMobile);
            redirectAttributes.addFlashAttribute("message",ex.getMessage());
            return "redirect:/";
        }
    }

    /**
     * ��¼�����ҳ
     * @return
     */
    @GetMapping("/home")
    public String home() {
        return "home";
    }

}
