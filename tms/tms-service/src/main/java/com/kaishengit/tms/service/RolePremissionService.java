package com.kaishengit.tms.service;

import com.kaishengit.tms.entity.Premission;
import com.kaishengit.tms.entity.Role;

import java.util.List;
/**
 *��ɫ��Ȩ��ҵ����
 * @author ���
 * @date 2018/4/13
 */
public interface RolePremissionService {
    /**
     *�鿴����Ȩ��
     * @author ���
     * @date 2018/4/13
     */
    List<Premission> findAllPermission();
    /**
     *����Ȩ�޵�����
     * @date 2018/4/13
     * @param premissionType
     * @return java.util.List<com.kaishengit.tms.entity.Premission>
     */
    List<Premission> findPremissionByPremissionType(String premissionType);
    /**
     *���Ȩ��
     * @date 2018/4/13
     * @param premission
     * @return void
     */
    void savePression(Premission premission);
/**
 *������ɫ
 * @date 2018/4/15
 * @param role,��ɫ���� premissionId����ɫ��Ӧ�ĵ�ID
 * @return void
 */
    void saveRole(Role role, Integer[] premissionId);
/**
 *��ѯ���н�ɫ
 * @date 2018/4/16
 * @param
 * @return java.util.List<com.kaishengit.tms.entity.Role>
 */
    List<Role>  findAllRoles();
}
