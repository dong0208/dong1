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
     * 查看所有权限
     *
     * @author 杨冬冬
     * @date 2018/4/13
     */
    List<Permission> findAllPermission();

    /**
     * 查找权限的类型
     *
     * @param permissionType
     * @return java.util.List<com.kaishengit.tms.entity.Permission>
     * @date 2018/4/13
     */
    List<Permission> findPermissionByPermissionType(String permissionType);

    /**
     * 添加权限
     *
     * @param permission
     * @return void
     * @date 2018/4/13
     */

    void savePermission(Permission permission);

    /**
     * 新增角色
     *
     * @param roles,角色对象， premissionId，角色对应的的ID
     * @return void
     * @date 2018/4/15
     */
    void saveRole(Roles roles, Integer[] permissionId);

    /**
     * 查询所有角色
     *
     * @param
     * @return java.util.List<com.kaishengit.tms.entity.Role>
     * @date 2018/4/16
     */
    List<Roles> findAllRoles();

    /**
     * 根据id查找用户权限
     *
     * @param id
     * @return java.util.List<com.kaishengit.tms.entity.Permission>
     * @date 2018/4/17
     */
    Permission findAllPermissionById(Integer id);

    /**
     * 修改权限
     *
     * @param permission, id
     * @return void
     * @date 2018/4/17
     */
    void updatePermission(Permission permission, Integer id);

    /**
     * 根据id删除权限
     *
     * @param id
     * @return void
     * @date 2018/4/17
     */
    void delPermission(Integer id);

    /**
     * 根据账号ID查找对应角色的集合
     *
     * @param id
     * @return
     */
    List<Roles> findRolesByAccountId(Integer id);

    /**
     * 根据角色ID查找对应的权限
     *
     * @param id
     * @return
     */
    List<Permission> findAllPermissionByRolesId(Integer id);

    /**
     * 查询所有角色并加载对应的角色列表
     *
     * @return
     */
    List<Roles> findAllRolesWithPermission();

    /**
     * 删除角色
     *
     * @param id
     */
    void delRolesyId(Integer id);

    /**根据角色id查找对应权限
     * @param id
     * @return
     */
    Roles findRolesWithPermissionById(Integer id);

    /**
     * 修改角色对象
     * @param roles
     * @param permissionId
     */
    void updateRoles(Roles roles, Integer[] permissionId);
}