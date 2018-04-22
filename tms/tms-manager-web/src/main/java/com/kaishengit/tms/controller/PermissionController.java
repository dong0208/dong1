package com.kaishengit.tms.controller;


import com.kaishengit.tms.dto.ResponseBean;
import com.kaishengit.tms.entity.Permission;
import com.kaishengit.tms.controller.exception.ServiceException;
import com.kaishengit.tms.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/*
 *权限控制器
 * @author 杨冬冬
 * @date 2018/4/13
 */
@Controller
@RequestMapping("/manage/permission")
public class PermissionController {
    @Autowired
    private RolePermissionService rolePermissionService;
    /*  
     *权限的首页
     * @date 2018/4/13
     * @param [model]  
     * @return java.lang.String  
     */ 
    @GetMapping
    public String home(Model model){
       List<Permission> permissionList = rolePermissionService.findAllPermission();
        System.out.println("permissionList :" + permissionList);
        model.addAttribute("permissionList",permissionList);
        return "manage/permission/home";
    }
    /*  
     *新增权限
     * @date 2018/4/13
     * @param [model]  
     * @return java.lang.String  
     */ 
    @GetMapping("/new")
    public  String newPermission(Model model){
        //查询菜单类型的权限列表
        List<Permission> permissionList = rolePermissionService.findPermissionByPermissionType(Permission.MENU_TYPE);
        model.addAttribute("permissionList",permissionList);

        return "manage/permission/new";
    }
    @PostMapping("/new")
    public  String newPermission(Permission permission, RedirectAttributes redirectAttributes){
            rolePermissionService.savePermission(permission);
            redirectAttributes.addFlashAttribute("message","新增权限成功");
            return "redirect:/manage/permission";

    }
    /*
     *修改?限
     * @date 2018/4/17
     * @param [id, model]
     * @return java.lang.String
     */
    @GetMapping("/{id:\\d+}/edit")
    public String updatePermission(@PathVariable Integer id,Model model){
       Permission permission = rolePermissionService.findAllPermissionById(id);


        model.addAttribute("permission",permission);

        return "manage/permission/edit";
}

    @PostMapping("/{id:\\d+}/edit")

    public String updatePermission(Permission permission,Integer id,RedirectAttributes redirectAttributes){
        rolePermissionService.updatePermission(permission,id);
        redirectAttributes.addFlashAttribute("message","修改成功");
        return "redirect:/manage/permission";
    }

    /*
     *删除权限
     * @date 2018/4/17
     * @param [id]
     * @return com.kaishengit.tms.dto.ResponseBean
     */
    @GetMapping("/{id:\\d+}/del")
    public @ResponseBody ResponseBean delPermission(@PathVariable Integer id){
        try {
            rolePermissionService.delPermission(id);
            return ResponseBean.success();
        }catch (ServiceException ex){
            return  ResponseBean.error(ex.getMessage());
        }

    }

}
