package com.kaishengit.tms.controller;

import com.google.common.collect.Maps;
import com.kaishengit.tms.dto.ResponseBean;
import com.kaishengit.tms.entity.Permission;
import com.kaishengit.tms.entity.Roles;
import com.kaishengit.tms.exception.NotFoundException;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.service.RolePermissionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manage/roles")
public class RoleController {
    @Autowired
    private RolePermissionService rolePermissionService;
/*
 *���ҽ�ɫ��ӦȨ��
 * @date 2018/4/18
 * @param [model]
 * @return java.lang.String
 */
    @GetMapping
    public String home(Model model) {
        model.addAttribute("rolesList",rolePermissionService.findAllRolesWithPermission());
        return "manage/roles/home";
    }

    /*
     *������ɫ
     * ��ѯȨ���б�
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
        redirectAttributes.addFlashAttribute("message","�����ɹ�");
        return "redirect:/manage/roles";
    }
    /**
     * ɾ����ɫ
     */
    @GetMapping("/{id:\\d+}/del")

    public @ResponseBody ResponseBean deleteRoles(@PathVariable Integer id){
        try {

            rolePermissionService.delRolesyId(id);
            return ResponseBean.success();
        }catch (ServiceException ex){
            return ResponseBean.error(ex.getMessage());
        }
    }
    /*
     *�༭��ɫ
     * @date 2018/4/20
     * @param [id, model]
     * @return java.lang.String
     */
   @GetMapping("/{id:\\d+}/edit")
    public String updateRoles(@PathVariable Integer id,Model model){
       //��ѯ��ɫ����ɫӵ�е�Ȩ��
       Roles roles = rolePermissionService.findRolesWithPermissionById(id);

       if(roles == null) {
           throw new NotFoundException();
       }
       //��ѯ���е�Ȩ���б�
       List<Permission> permissionList = rolePermissionService.findAllPermission();

       //�ж�Ȩ���б��Ƿ�ñ�checked
       Map<Permission,Boolean> map = checkdPermissionList(roles.getPermissionList(),permissionList);

       model.addAttribute("roles",roles);
       model.addAttribute("permissionMap",map);
       return "manage/roles/edit";
   }

    /*
     *�ڱ༭ҳ���жϵ�ǰȨ�޵ĸ�ѡ���Ƿ�checked
     * @date 2018/4/20
     * @param
     * @return
     */
    
    private Map<Permission,Boolean> checkdPermissionList(List<Permission> rolesPermissionList,List<Permission> permissionList) {
        Map<Permission,Boolean> resultMap = Maps.newLinkedHashMap();

        for(Permission permission : permissionList) {
            boolean flag = false;
            for(Permission rolesPermission : rolesPermissionList) {
                if(permission.getId().equals(rolesPermission.getId())) {
                    flag = true;
                    break;
                }
            }
            resultMap.put(permission,flag);
        }
        return resultMap;
   }
    @PostMapping("/{id:\\d+}/edit")
    public String editRoles(Roles roles,Integer[] permissionId,
                            RedirectAttributes redirectAttributes) {
        rolePermissionService.updateRoles(roles,permissionId);

        redirectAttributes.addFlashAttribute("message","��ɫ�޸ĳɹ�");
        return "redirect:/manage/roles";
    }

}
