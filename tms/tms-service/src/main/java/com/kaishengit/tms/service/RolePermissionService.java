package com.kaishengit.tms.service;

import com.kaishengit.tms.entity.Permission;
import com.kaishengit.tms.entity.Roles;

import java.util.List;
/**
 *��ɫ��Ȩ��ҵ����
 * @author ���
 * @date 2018/4/13
 */
public interface RolePermissionService {
    /**
     *�鿴����Ȩ��
     * @author ���
     * @date 2018/4/13
     */
    List<Permission> findAllPermission();
    /**
     *����Ȩ�޵�����
     * @date 2018/4/13
     * @param permissionType
     * @return java.util.List<com.kaishengit.tms.entity.Permission>
     */
    List<Permission> findPermissionByPermissionType(String permissionType);
    /**
     *���Ȩ��
     * @date 2018/4/13
     * @param permission
     * @return void
     */

    void savePermission(Permission permission);

/**
 *������ɫ
 * @date 2018/4/15
 * @param roles,��ɫ���� premissionId����ɫ��Ӧ�ĵ�ID
 * @return void
 */
    void saveRole(Roles roles, Integer[] permissionId);

/**
 *��ѯ���н�ɫ
 * @date 2018/4/16
 * @param
 * @return java.util.List<com.kaishengit.tms.entity.Role>
 */
    List<Roles>  findAllRoles();
}
