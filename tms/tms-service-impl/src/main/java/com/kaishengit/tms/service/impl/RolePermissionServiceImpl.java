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
 *角色和权限的业务类
 * @author 杨冬冬
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
     * 查看所有权限
     *
     * @author 杨冬冬
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
     * 查找权限的类型
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
     * 添加权限
     *
     * @param permission
     * @return void
     * @date 2018/4/13
     */
    @Override
    public void savePermission(Permission permission) {
        permission.setCreateTime(new Date());
        permissionMapper.insertSelective(permission);
        logger.info("添加权限{}",permission);
    }

    /**
     * 新增角色
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
        //保存角色和权限的关系
        if(premissionId != null){
            for (Integer preId:premissionId){
                RolesPermissionKey rolesPermissionKey = new RolesPermissionKey();
                rolesPermissionKey.setPermissionId(preId);
                rolesPermissionKey.setRolesId(roles.getId());

                rolesPermissionKeyMapper.insert(rolesPermissionKey);
            }
        }
        logger.info("保存角色{}",roles);
    }

    /**
     * 查询所有角色
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
     * 根据id查找用户权限
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
     * 修改权限
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
        logger.info("修改权限 {}",permission);
    }

    /**
     * 根据id删除权限
     *
     * @param id
     * @return void
     * @date 2018/4/17
     */
    @Override
    public void delPermission(Integer id) {
        //查询该权限是否有子节点
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.createCriteria().andParentIdEqualTo(id);

        List<Permission> permissionList = permissionMapper.selectByExample(permissionExample);
        if(permissionList != null && !permissionList.isEmpty()) {
            throw new ServiceException("该权限下有子节点,删除失败");
        }

        //查询权限是否被角色使用
        RolesPermissionExample rolesPermissionExample = new RolesPermissionExample();
        rolesPermissionExample.createCriteria().andPermissionIdEqualTo(id);

        List<RolesPermissionKey> rolesPermissionKeyList = rolesPermissionMapper.selectByExample(rolesPermissionExample);
        if(rolesPermissionKeyList != null && !rolesPermissionKeyList.isEmpty()) {
            throw new ServiceException("该权限被角色引用，删除失败");
        }
        //如果没有被使用，则可以直接删除
        Permission permission = permissionMapper.selectByPrimaryKey(id);
        permissionMapper.deleteByPrimaryKey(id);
        logger.info("删除权限 {}",permission);
    }

    /**
     * 根据账号ID查找对应角色的集合
     *
     * @param id
     * @return
     */
    @Override
    public List<Roles> findRolesByAccountId(Integer id) {
        return rolesMapper.findRolesByAccountId(id);
    }

    /**
     * 根据角色ID查找对应的权限
     *
     * @param rolesId
     * @return
     */
    @Override
    public List<Permission> findAllPermissionByRolesId(Integer rolesId) {
        return  permissionMapper.findAllByRolesId(rolesId);
    }

    /**
     * 查询所有角色并加载对应的角色列表
     *
     * @return
     */
    @Override
    public List<Roles> findAllRolesWithPermission() {

        return rolesMapper.findAllWithPermission();
}

    /**
     * 删除角色
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delRolesyId(Integer id) throws ServiceException{
        //查询角色是否被账户引用，如果引用则不能删除
        AccountRolesExample accountRolesExample = new AccountRolesExample();
        accountRolesExample.createCriteria().andRolesIdEqualTo(id);

        List<AccountRolesKey> accountRolesKeys = accountRolesMapper.selectByExample(accountRolesExample);
        if (accountRolesKeys != null && !accountRolesKeys.isEmpty()){
            throw new ServiceException("该角色已被账号使用，删除失败");
        }
        //删除角色和权限关系的记录
        RolesPermissionExample rolesPermissionExample = new RolesPermissionExample();
        rolesPermissionExample.createCriteria().andRolesIdEqualTo(id);

        rolesPermissionKeyMapper.deleteByExample(rolesPermissionExample);
        //删除角色
        Roles roles = rolesMapper.selectByPrimaryKey(id);
        rolesMapper.deleteByPrimaryKey(id);
        logger.info("删除角色 {}",roles);
    }

    /**
     * 根据角色id查找对应权限
     *
     * @param id
     * @return
     */
    @Override
    public Roles findRolesWithPermissionById(Integer id) {

        return rolesMapper.findRolesWithPermission(id);
    }

    /**
     * 修改角色对象
     *
     * @param roles
     * @param permissionId
     */
    @Override
    public void updateRoles(Roles roles, Integer[] permissionId) {
        //将角色原有和权限的对应关系删除
        RolesPermissionExample rolesPermissionExample = new RolesPermissionExample();
        rolesPermissionExample.createCriteria().andRolesIdEqualTo(roles.getId());

        rolesPermissionMapper.deleteByExample(rolesPermissionExample);

        //重建角色和权限的对应关系
        if (permissionId != null) {
            for (Integer perId : permissionId) {
                RolesPermissionKey rolesPermissionKey = new RolesPermissionKey();
                rolesPermissionKey.setRolesId(roles.getId());
                rolesPermissionKey.setPermissionId(perId);
                rolesPermissionMapper.insert(rolesPermissionKey);
            }
            //修改角色对象
            rolesMapper.updateByPrimaryKeySelective(roles);
        }
        logger.info("修改角色 {}",roles);
    }
    /**
     * 将查询数据库的权限列表转换为树形集合结果
     * @param sourceList 数据库查询出的集合
     * @param endList 转换结束的结果集合
     * @param parentId 父ID
     */
    private void treeList(List<Permission> sourceList, List<Permission> endList, int parentId) {

        List<Permission> tempList = Lists.newArrayList(Collections2.filter(sourceList, permission -> permission.getParentId().equals(parentId)));
        for(Permission permission : tempList) {
            endList.add(permission);
            treeList(sourceList,endList,permission.getId());
        }
    }

}

