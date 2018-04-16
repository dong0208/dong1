package com.kaishengit.tms.controller;

import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.Role;
import com.kaishengit.tms.service.AccountService;
import com.kaishengit.tms.service.RolePremissionService;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
/*
 *
 * @author Ñî¶¬¶¬
 * @date 2018/4/16
 */
@Controller
@RequestMapping("/manage/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private RolePremissionService rolePermissionService;
    @GetMapping
    public String home(Model model,
                       @RequestParam(required = false) Integer roleId,
                       @RequestParam(required = false) String nameMobile){
        Map<String,Object> requestParam = Maps.newHashMap();

        requestParam.put("nameMobile",nameMobile);
        requestParam.put("rolesId",roleId);

        model.addAttribute("accountList",accountService.findAllAccountWithRolesByQueryParam(requestParam));
        model.addAttribute("rolesList",rolePermissionService.findAllRoles());
        return "manage/account/home";
    }
    /**
     * ÐÂÔö½ÇÉ«
     * @param model
     * @return
     */
    @GetMapping("/new")
    public String newAccount(Model model) {
        List<Role> rolesList = rolePermissionService.findAllRoles();

        model.addAttribute("rolesList",rolesList);
        return "manage/account/new";
    }

    @PostMapping("/new")
    public String newAccount(Account account, Integer[] rolesIds) {
        accountService.saveAccount(account,rolesIds);
        return "redirect:/manage/account";
    }

}
