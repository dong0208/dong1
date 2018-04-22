package com.kaishengit.tms.service.impl;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.kaishengit.tms.entity.*;
import com.kaishengit.tms.controller.exception.ServiceException;
import com.kaishengit.tms.mapper.AccountRolesMapper;
import com.kaishengit.tms.mapper.PermissionMapper;
import com.kaishengit.tms.mapper.RolesMapper;
import com.kaishengit.tms.mapper.RolesPermissionMapper;
import com.kaishengit.tms.service.RolePermissionService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    @Autowired
    private RolesPermissionMapper rolesPermissionMapper;
    @Autowired
    private AccountRolesMapper accountRolesMapper;

    /**
     * �鿴����Ȩ��
     *
     * @author ���
     * @date 2018/4/13
     */

    @Override
    public List<Permission> findAllPermission() {
        PermissionExample permissionExample = new PermissionExample();

        List<Permission> permissionList = permissionMapper.selectByExample(permissionExample);
        List<Permission> resultList = new ArrayList<>();
        treeList(permissionList,resultList,0);
        System.out.println("resultList" + resultList);
        return resultList;
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
        if(premissionId != null){
            for (Integer preId:premissionId){
                RolesPermissionKey rolesPermissionKey = new RolesPermissionKey();
                rolesPermissionKey.setPermissionId(preId);
                rolesPermissionKey.setRolesId(roles.getId());

                rolesPermissionKeyMapper.insert(rolesPermissionKey);
            }
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

    /**
     * ����id�����û�Ȩ��
     *
     * @param id
     * @return java.util.List<com.kaishengit.tms.entity.Permission>
     * @date 2018/4/17
     */
    @Override

    public Permission findAllPermissionById(Integer id) {

        return permissionMapper.selectByPrimaryKey(id);
    }

    /**
     * �޸�Ȩ��
     *
     * @param permission
     * @param id
     * @return void
     * @date 2018/4/17
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updatePermission(Permission permission, Integer id) {
        permission.setUpdateTime(new Date());
        permissionMapper.updateByPrimaryKeySelective(permission);
        logger.info("�޸�Ȩ�� {}",permission);
    }

    /**
     * ����idɾ��Ȩ��
     *
     * @param id
     * @return void
     * @date 2018/4/17
     */
    @Override
    public void delPermission(Integer id) {
        //��ѯ��Ȩ���Ƿ����ӽڵ�
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.createCriteria().andParentIdEqualTo(id);

        List<Permission> permissionList = permissionMapper.selectByExample(permissionExample);
        if(permissionList != null && !permissionList.isEmpty()) {
            throw new ServiceException("��Ȩ�������ӽڵ�,ɾ��ʧ��");
        }

        //��ѯȨ���Ƿ񱻽�ɫʹ��
        RolesPermissionExample rolesPermissionExample = new RolesPermissionExample();
        rolesPermissionExample.createCriteria().andPermissionIdEqualTo(id);

        List<RolesPermissionKey> rolesPermissionKeyList = rolesPermissionMapper.selectByExample(rolesPermissionExample);
        if(rolesPermissionKeyList != null && !rolesPermissionKeyList.isEmpty()) {
            throw new ServiceException("��Ȩ�ޱ���ɫ���ã�ɾ��ʧ��");
        }
        //���û�б�ʹ�ã������ֱ��ɾ��
        Permission permission = permissionMapper.selectByPrimaryKey(id);
        permissionMapper.deleteByPrimaryKey(id);
        logger.info("ɾ��Ȩ�� {}",permission);
    }

    /**
     * �����˺�ID���Ҷ�Ӧ��ɫ�ļ���
     *
     * @param id
     * @return
     */
    @Override
    public List<Roles> findRolesByAccountId(Integer id) {
        return rolesMapper.findRolesByAccountId(id);
    }

    /**
     * ���ݽ�ɫID���Ҷ�Ӧ��Ȩ��
     *
     * @param rolesId
     * @return
     */
    @Override
    public List<Permission> findAllPermissionByRolesId(Integer rolesId) {
        return  permissionMapper.findAllByRolesId(rolesId);
    }

    /**
     * ��ѯ���н�ɫ�����ض�Ӧ�Ľ�ɫ�б�
     *
     * @return
     */
    @Override
    public List<Roles> findAllRolesWithPermission() {

        return rolesMapper.findAllWithPermission();
}

    /**
     * ɾ����ɫ
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delRolesyId(Integer id) throws ServiceException{
        //��ѯ��ɫ�Ƿ��˻����ã������������ɾ��
        AccountRolesExample accountRolesExample = new AccountRolesExample();
        accountRolesExample.createCriteria().andRolesIdEqualTo(id);

        List<AccountRolesKey> accountRolesKeys = accountRolesMapper.selectByExample(accountRolesExample);
        if (accountRolesKeys != null && !accountRolesKeys.isEmpty()){
            throw new ServiceException("�ý�ɫ�ѱ��˺�ʹ�ã�ɾ��ʧ��");
        }
        //ɾ����ɫ��Ȩ�޹�ϵ�ļ�¼
        RolesPermissionExample rolesPermissionExample = new RolesPermissionExample();
        rolesPermissionExample.createCriteria().andRolesIdEqualTo(id);

        rolesPermissionKeyMapper.deleteByExample(rolesPermissionExample);
        //ɾ����ɫ
        Roles roles = rolesMapper.selectByPrimaryKey(id);
        rolesMapper.deleteByPrimaryKey(id);
        logger.info("ɾ����ɫ {}",roles);
    }

    /**
     * ���ݽ�ɫid���Ҷ�ӦȨ��
     *
     * @param id
     * @return
     */
    @Override
    public Roles findRolesWithPermissionById(Integer id) {

        return rolesMapper.findRolesWithPermission(id);
    }

    /**
     * �޸Ľ�ɫ����
     *
     * @param roles
     * @param permissionId
     */
    @Override
    public void updateRoles(Roles roles, Integer[] permissionId) {
        //����ɫԭ�к�Ȩ�޵Ķ�Ӧ��ϵɾ��
        RolesPermissionExample rolesPermissionExample = new RolesPermissionExample();
        rolesPermissionExample.createCriteria().andRolesIdEqualTo(roles.getId());

        rolesPermissionMapper.deleteByExample(rolesPermissionExample);

        //�ؽ���ɫ��Ȩ�޵Ķ�Ӧ��ϵ
        if (permissionId != null) {
            for (Integer perId : permissionId) {
                RolesPermissionKey rolesPermissionKey = new RolesPermissionKey();
                rolesPermissionKey.setRolesId(roles.getId());
                rolesPermissionKey.setPermissionId(perId);
                rolesPermissionMapper.insert(rolesPermissionKey);
            }
            //�޸Ľ�ɫ����
            rolesMapper.updateByPrimaryKeySelective(roles);
        }
        logger.info("�޸Ľ�ɫ {}",roles);
    }
    /**
     * ����ѯ���ݿ��Ȩ���б�ת��Ϊ���μ��Ͻ��
     * @param sourceList ���ݿ��ѯ���ļ���
     * @param endList ת�������Ľ������
     * @param parentId ��ID
     */
    private void treeList(List<Permission> sourceList, List<Permission> endList, int parentId) {

        List<Permission> tempList = Lists.newArrayList(Collections2.filter(sourceList, permission -> permission.getParentId().equals(parentId)));
        for(Permission permission : tempList) {
            endList.add(permission);
            treeList(sourceList,endList,permission.getId());
        }
    }

}

