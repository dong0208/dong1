package com.kaishengit.tms.controller;

import com.kaishengit.tms.entity.Role;
import com.kaishengit.tms.service.RolePremissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/manage/role")
public class RoleController {
    @Autowired
    private RolePremissionService rolePremissionService;

    @GetMapping
    public String home() {
        return "manage/role/home";
    }

    /*
     *新增角色
     * @date 2018/4/15
     * @param [model]
     * @return java.lang.String
     */
    @GetMapping("/new")
    public String newRole(Model model) {
        model.addAttribute("premissionList", rolePremissionService.findAllPermission());
        return "manage/role/new";
    }

    @PostMapping("/new")
    public String newRole(Role role, Integer[] premissionId, RedirectAttributes redirectAttributes){
        rolePremissionService.saveRole(role,premissionId);
        redirectAttributes.addFlashAttribute("message","新增成功");
        return "redirect:/manage/role";
    }

}
