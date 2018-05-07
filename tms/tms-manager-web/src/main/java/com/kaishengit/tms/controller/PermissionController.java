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
 *
 * @author
 * @date 2018/4/13
 */
@Controller
@RequestMapping("/manage/permission")
public class PermissionController {
    @Autowired
    private RolePermissionService rolePermissionService;
    /*  

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
     *
     * @date 2018/4/13
     * @param [model]  
     * @return java.lang.String  
     */ 
    @GetMapping("/new")
    public  String newPermission(Model model){
        //
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
    /*
     *
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
        redirectAttributes.addFlashAttribute("message","�޸ĳɹ�");
        return "redirect:/manage/permission";
    }

    /*
     *
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
