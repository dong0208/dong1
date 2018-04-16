package com.kaishengit.tms.service.impl;

import com.kaishengit.tms.entity.*;
import com.kaishengit.tms.mapper.PermissionMapper;
import com.kaishengit.tms.mapper.RolesMapper;
import com.kaishengit.tms.mapper.RolesPermissionMapper;
import com.kaishengit.tms.service.RolePermissionService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

/* *
 *��ɫ��Ȩ�޵�ҵ����
 * @author ���
 * @date 2018/4/13
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {
    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(RolePermissionServiceImpl.class);
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RolesMapper rolesMapper;
    @Autowired
    private RolesPermissionMapper rolesPermissionKeyMapper;
    /**
     * �鿴����Ȩ��
     *
     * @author ���
     * @date 2018/4/13
     */

    @Override
    public List<Permission> findAllPermission() {
        PermissionExample permissionExample = new PermissionExample();
        return permissionMapper.selectByExample(permissionExample);


    }



    /**
     * ����Ȩ�޵�����
     *
     * @param permissionType
     * @return java.util.List<com.kaishengit.tms.entity.Permission>
     * @date 2018/4/13
     */
    @Override
    public List<Permission> findPermissionByPermissionType(String permissionType) {
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.createCriteria().andPermissionTypeEqualTo(permissionType);
        return permissionMapper.selectByExample(permissionExample);
    }



    /**
     * ���Ȩ��
     *
     * @param permission
     * @return void
     * @date 2018/4/13
     */
    @Override
    public void savePermission(Permission permission) {
        permission.setCreateTime(new Date());
        permissionMapper.insertSelective(permission);
        logger.info("���Ȩ��{}",permission);
    }

    /**
     * ������ɫ
     *
     * @param roles
     * @param premissionId
     * @return void
     * @date 2018/4/15
     */
    @Override
    public void saveRole(Roles roles, Integer[] premissionId) {
        roles.setCreateTime(new Date());
        rolesMapper.insertSelective(roles);
        //�����ɫ��Ȩ�޵Ĺ�ϵ
        for (Integer preId:premissionId){
            RolesPermissionKey rolesPermissionKey = new RolesPermissionKey();
            rolesPermissionKey.setPermissionId(preId);
            rolesPermissionKey.setRolesId(roles.getId());

            rolesPermissionKeyMapper.insert(rolesPermissionKey);
        }
        logger.info("�����ɫ{}",roles);
    }

    /**
     * ��ѯ���н�ɫ
     *
     * @return java.util.List<com.kaishengit.tms.entity.Role>
     * @date 2018/4/16
     */
    @Override
    public List<Roles> findAllRoles() {
        RolesExample rolesExample = new RolesExample();
        return rolesMapper.selectByExample(rolesExample);
    }
}
