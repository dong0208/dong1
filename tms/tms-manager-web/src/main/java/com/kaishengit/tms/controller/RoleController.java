package com.kaishengit.tms.controller;

import com.kaishengit.tms.entity.Roles;
import com.kaishengit.tms.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/manage/roles")
public class RoleController {
    @Autowired
    private RolePermissionService rolePermissionService;

    @GetMapping
    public String home() {
        return "manage/roles/home";
    }

    /*
     *新增角色
     * @date 2018/4/15
     * @param [model]
     * @return java.lang.String
     */
    @GetMapping("/new")
    public String newRole(Model model) {
        model.addAttribute("permissionList", rolePermissionService.findAllPermission());
        return "manage/roles/new";
    }

    @PostMapping("/new")
    public String newRole(Roles roles, Integer[] permissionId, RedirectAttributes redirectAttributes){
        rolePermissionService.saveRole(roles,permissionId);
        redirectAttributes.addFlashAttribute("message","新增成功");
        return "redirect:/manage/roles";
    }

}
