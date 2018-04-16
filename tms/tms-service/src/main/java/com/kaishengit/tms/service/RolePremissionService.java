package com.kaishengit.tms.service;

import com.kaishengit.tms.entity.Premission;
import com.kaishengit.tms.entity.Role;

import java.util.List;
/**
 *角色和权限业务类
 * @author 杨冬冬
 * @date 2018/4/13
 */
public interface RolePremissionService {
    /**
     *查看所有权限
     * @author 杨冬冬
     * @date 2018/4/13
     */
    List<Premission> findAllPermission();
    /**
     *查找权限的类型
     * @date 2018/4/13
     * @param premissionType
     * @return java.util.List<com.kaishengit.tms.entity.Premission>
     */
    List<Premission> findPremissionByPremissionType(String premissionType);
    /**
     *添加权限
     * @date 2018/4/13
     * @param premission
     * @return void
     */
    void savePression(Premission premission);
/**
 *新增角色
 * @date 2018/4/15
 * @param role,角色对象， premissionId，角色对应的的ID
 * @return void
 */
    void saveRole(Role role, Integer[] premissionId);
/**
 *查询所有角色
 * @date 2018/4/16
 * @param
 * @return java.util.List<com.kaishengit.tms.entity.Role>
 */
    List<Role>  findAllRoles();
}
