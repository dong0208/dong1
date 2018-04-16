package com.kaishengit.tms.controller;

import com.kaishengit.tms.entity.Premission;
import com.kaishengit.tms.service.RolePremissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/*
 *权限控制器
 * @author 杨冬冬
 * @date 2018/4/13
 */
@Controller
@RequestMapping("/manage/premission")
public class PermissionController {
    @Autowired
    private RolePremissionService rolePremissionService;
    /*  
     *权限的首页
     * @date 2018/4/13
     * @param [model]  
     * @return java.lang.String  
     */ 
    @GetMapping
    public String home(Model model){
       List<Premission> premissionList = rolePremissionService.findAllPermission();
        model.addAttribute("premissionList",premissionList);
        return "/manage/premission/home";
    }
    /*  
     *新增权限
     * @date 2018/4/13
     * @param [model]  
     * @return java.lang.String  
     */ 
    @GetMapping("/new")
    public  String newPremission(Model model){
        //查询菜单类型的权限列表
        List<Premission> premissionList = rolePremissionService.findPremissionByPremissionType(Premission.MENU_TYPE);
        model.addAttribute("premissionList",premissionList);

        return "manage/premission/new";
    }
    @PostMapping("/new")
    public  String newPremission(Premission premission, RedirectAttributes redirectAttributes){
            rolePremissionService.savePression(premission);
            redirectAttributes.addFlashAttribute("message","新增权限成功");
            return "redirect:/manage/premission";

    }

}
