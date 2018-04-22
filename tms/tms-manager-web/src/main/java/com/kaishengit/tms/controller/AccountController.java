package com.kaishengit.tms.controller;

import com.kaishengit.tms.entity.Account;

import com.kaishengit.tms.entity.Roles;
import com.kaishengit.tms.controller.exception.NotFoundException;
import com.kaishengit.tms.service.AccountService;
import com.kaishengit.tms.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
/*
 *
 * @author ���
 * @date 2018/4/16
 */
@Controller
@RequestMapping("/manage/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private RolePermissionService rolePermissionService;

    @GetMapping
    public String home(Model model,
                          @RequestParam(required = false) Integer rolesId,
                          @RequestParam(required = false) String nameMobile){
        Map<String,Object> requestParam = Maps.newHashMap();

        requestParam.put("nameMobile",nameMobile);
        requestParam.put("rolesId",rolesId);

        model.addAttribute("accountList",accountService.findAllAccountWithRolesByQueryParam(requestParam));
        model.addAttribute("rolesList",rolePermissionService.findAllRoles());
        return "manage/account/home";
    }
    /**
     * ����zhanghu
     * @param model
     * @return
     */
    @GetMapping("/new")
    public String newAccount(Model model) {
        List<Roles> rolesList = rolePermissionService.findAllRoles();

        model.addAttribute("rolesList",rolesList);
        return "manage/account/new";
    }

    @PostMapping("/new")
    public String newAccount(Account account, Integer[] rolesIds) {

        accountService.saveAccount(account,rolesIds);
        return "redirect:/manage/account";
    }
    /**
     * �޸��˺�
     */

    @GetMapping("/{id:\\d+}/edit")
    public String updateAccount(@PathVariable Integer id,Model model) {
        Account account = accountService.findAccountById(id);
        if (account == null){
            throw  new NotFoundException();
        }
        //��ѯ���н�ɫ
        List<Roles> rolesList = rolePermissionService.findAllRoles();
        //��ѯ��ǰ�˺�ӵ�еĽ�ɫ
        List<Roles> accountRolesList = rolePermissionService.findRolesByAccountId(id);
        model.addAttribute("rolesList",rolesList);
        model.addAttribute("accountRolesList",accountRolesList);
        model.addAttribute("account",account);
        return "manage/account/edit";
    }

    @PostMapping("/{id:\\d+}/edit")
    public String updateAccount(Account account, Integer[] rolesIds, RedirectAttributes redirectAttributes){
        accountService.updateAccount(account,rolesIds);
        redirectAttributes.addFlashAttribute("message","�޸��˻��ɹ�");
        return "redirect:/manage/account";
    }
}
