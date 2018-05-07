package com.kaishengit.tms.service;

import com.kaishengit.tms.entity.Permission;
import com.kaishengit.tms.entity.Roles;

import java.util.List;
/**
 *
 * @author
 * @date 2018/4/13
 */
public interface RolePermissionService {
    /**
     *
     *
     * @author
     * @date 2018/4/13
     */
    List<Permission> findAllPermission();

    /**
     *
     *
     * @param permissionType
     * @return java.util.List<com.kaishengit.tms.entity.Permission>
     * @date 2018/4/13
     */
    List<Permission> findPermissionByPermissionType(String permissionType);

    /**
     *
     *
     * @param permission
     * @return void
     * @date 2018/4/13
     */

    void savePermission(Permission permission);

    /**
     *
     *
     * @param roles, premissionId
     * @return void
     * @date 2018/4/15
     */
    void saveRole(Roles roles, Integer[] permissionId);

    /**
     *
     *
     * @param
     * @return java.util.List<com.kaishengit.tms.entity.Role>
     * @date 2018/4/16
     */
    List<Roles> findAllRoles();

    /**
     *
     *
     * @param id
     * @return java.util.List<com.kaishengit.tms.entity.Permission>
     * @date 2018/4/17
     */
    Permission findAllPermissionById(Integer id);

    /**
     *
     *
     * @param permission, id
     * @return void
     * @date 2018/4/17
     */
    void updatePermission(Permission permission, Integer id);

    /**
     *
     *
     * @param id
     * @return void
     * @date 2018/4/17
     */
    void delPermission(Integer id);

    /**
     *
     *
     * @param id
     * @return
     */
    List<Roles> findRolesByAccountId(Integer id);

    /**
     *
     *
     * @param id
     * @return
     */
    List<Permission> findAllPermissionByRolesId(Integer id);

    /**
     *
     *
     * @return
     */
    List<Roles> findAllRolesWithPermission();

    /**
     *
     *
     * @param id
     */
    void delRolesyId(Integer id);

    /**
     * @param id
     * @return
     */
    Roles findRolesWithPermissionById(Integer id);

    /**
     *
     * @param roles
     * @param permissionId
     */
    void updateRoles(Roles roles, Integer[] permissionId);
}