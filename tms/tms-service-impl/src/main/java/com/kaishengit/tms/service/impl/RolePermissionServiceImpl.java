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
    /**
     * 查看所有权限
     *
     * @author 杨冬冬
     * @date 2018/4/13
     */

    @Override
    public List<Permission> findAllPermission() {
        PermissionExample permissionExample = new PermissionExample();
        return permissionMapper.selectByExample(permissionExample);


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
        for (Integer preId:premissionId){
            RolesPermissionKey rolesPermissionKey = new RolesPermissionKey();
            rolesPermissionKey.setPermissionId(preId);
            rolesPermissionKey.setRolesId(roles.getId());

            rolesPermissionKeyMapper.insert(rolesPermissionKey);
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
}
