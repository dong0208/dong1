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
     * �鿴����Ȩ��
     *
     * @author ���
     * @date 2018/4/13
     */
    List<Permission> findAllPermission();

    /**
     * ����Ȩ�޵�����
     *
     * @param permissionType
     * @return java.util.List<com.kaishengit.tms.entity.Permission>
     * @date 2018/4/13
     */
    List<Permission> findPermissionByPermissionType(String permissionType);

    /**
     * ���Ȩ��
     *
     * @param permission
     * @return void
     * @date 2018/4/13
     */

    void savePermission(Permission permission);

    /**
     * ������ɫ
     *
     * @param roles,��ɫ���� premissionId����ɫ��Ӧ�ĵ�ID
     * @return void
     * @date 2018/4/15
     */
    void saveRole(Roles roles, Integer[] permissionId);

    /**
     * ��ѯ���н�ɫ
     *
     * @param
     * @return java.util.List<com.kaishengit.tms.entity.Role>
     * @date 2018/4/16
     */
    List<Roles> findAllRoles();

    /**
     * ����id�����û�Ȩ��
     *
     * @param id
     * @return java.util.List<com.kaishengit.tms.entity.Permission>
     * @date 2018/4/17
     */
    Permission findAllPermissionById(Integer id);

    /**
     * �޸�Ȩ��
     *
     * @param permission, id
     * @return void
     * @date 2018/4/17
     */
    void updatePermission(Permission permission, Integer id);

    /**
     * ����idɾ��Ȩ��
     *
     * @param id
     * @return void
     * @date 2018/4/17
     */
    void delPermission(Integer id);

    /**
     * �����˺�ID���Ҷ�Ӧ��ɫ�ļ���
     *
     * @param id
     * @return
     */
    List<Roles> findRolesByAccountId(Integer id);

    /**
     * ���ݽ�ɫID���Ҷ�Ӧ��Ȩ��
     *
     * @param id
     * @return
     */
    List<Permission> findAllPermissionByRolesId(Integer id);

    /**
     * ��ѯ���н�ɫ�����ض�Ӧ�Ľ�ɫ�б�
     *
     * @return
     */
    List<Roles> findAllRolesWithPermission();

    /**
     * ɾ����ɫ
     *
     * @param id
     */
    void delRolesyId(Integer id);

    /**���ݽ�ɫid���Ҷ�ӦȨ��
     * @param id
     * @return
     */
    Roles findRolesWithPermissionById(Integer id);

    /**
     * �޸Ľ�ɫ����
     * @param roles
     * @param permissionId
     */
    void updateRoles(Roles roles, Integer[] permissionId);
}