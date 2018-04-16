package com.kaishengit.tms.controller;


import com.kaishengit.tms.entity.Permission;
import com.kaishengit.tms.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/*
 *Ȩ�޿�����
 * @author ���
 * @date 2018/4/13
 */
@Controller
@RequestMapping("/manage/permission")
public class PermissionController {
    @Autowired
    private RolePermissionService rolePermissionService;
    /*  
     *Ȩ�޵���ҳ
     * @date 2018/4/13
     * @param [model]  
     * @return java.lang.String  
     */ 
    @GetMapping
    public String home(Model model){
       List<Permission> permissionList = rolePermissionService.findAllPermission();
        model.addAttribute("permissionList",permissionList);
        return "manage/permission/home";
    }
    /*  
     *����Ȩ��
     * @date 2018/4/13
     * @param [model]  
     * @return java.lang.String  
     */ 
    @GetMapping("/new")
    public  String newPermission(Model model){
        //��ѯ�˵����͵�Ȩ���б�
        List<Permission> permissionList = rolePermissionService.findPermissionByPermissionType(Permission.MENU_TYPE);
        model.addAttribute("permissionList",permissionList);

        return "manage/permission/new";
    }
    @PostMapping("/new")
    public  String newPermission(Permission permission, RedirectAttributes redirectAttributes){
            rolePermissionService.savePermission(permission);
            redirectAttributes.addFlashAttribute("message","����Ȩ�޳ɹ�");
            return "redirect:/manage/permission";

    }

}
