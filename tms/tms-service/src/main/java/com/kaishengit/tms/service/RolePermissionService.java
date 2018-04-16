package com.kaishengit.tms.service;

import com.kaishengit.tms.entity.Permission;
import com.kaishengit.tms.entity.Roles;

import java.util.List;
/**
 *角色和权限业务类
 * @author 杨冬冬
 * @date 2018/4/13
 */
public interface RolePermissionService {
    /**
     *查看所有权限
     * @author 杨冬冬
     * @date 2018/4/13
     */
    List<Permission> findAllPermission();
    /**
     *查找权限的类型
     * @date 2018/4/13
     * @param permissionType
     * @return java.util.List<com.kaishengit.tms.entity.Permission>
     */
    List<Permission> findPermissionByPermissionType(String permissionType);
    /**
     *添加权限
     * @date 2018/4/13
     * @param permission
     * @return void
     */

    void savePermission(Permission permission);

/**
 *新增角色
 * @date 2018/4/15
 * @param roles,角色对象， premissionId，角色对应的的ID
 * @return void
 */
    void saveRole(Roles roles, Integer[] permissionId);

/**
 *查询所有角色
 * @date 2018/4/16
 * @param
 * @return java.util.List<com.kaishengit.tms.entity.Role>
 */
    List<Roles>  findAllRoles();
}
