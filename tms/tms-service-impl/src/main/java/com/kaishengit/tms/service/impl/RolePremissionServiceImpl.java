package com.kaishengit.tms.service.impl;

import com.kaishengit.tms.entity.*;
import com.kaishengit.tms.mapper.PremissionMapper;
import com.kaishengit.tms.mapper.RoleMapper;
import com.kaishengit.tms.mapper.RolePremissionMapper;
import com.kaishengit.tms.service.RolePremissionService;
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
public class RolePremissionServiceImpl implements RolePremissionService {
    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(RolePremissionServiceImpl.class);
    @Autowired
    private PremissionMapper premissionMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RolePremissionMapper rolePremissionKeyMapper;
    /**
     * 查看所有权限
     *
     * @author 杨冬冬
     * @date 2018/4/13
     */
    @Override
    public List<Premission> findAllPermission() {
        PremissionExample premissionExample = new PremissionExample();
        return premissionMapper.selectByExample(premissionExample);


    }

    /**
     * 查找权限的类型
     *
     * @param premissionType
     * @return java.util.List<com.kaishengit.tms.entity.Premission>
     * @date 2018/4/13
     */
    @Override
    public List<Premission> findPremissionByPremissionType(String premissionType) {
        PremissionExample premissionExample = new PremissionExample();
        premissionExample.createCriteria().andPremissionTypeEqualTo(premissionType);
        return premissionMapper.selectByExample(premissionExample);
    }



    /**
     * 添加权限
     *
     * @param premission
     * @return void
     * @date 2018/4/13
     */
    @Override
    public void savePression(Premission premission) {
        premission.setCreateTime(new Date());
        premissionMapper.insertSelective(premission);
        logger.info("添加权限{}",premission);
    }

    /**
     * 新增角色
     *
     * @param role
     * @param premissionId
     * @return void
     * @date 2018/4/15
     */
    @Override
    public void saveRole(Role role, Integer[] premissionId) {
        role.setCreateTime(new Date());
        roleMapper.insertSelective(role);
        //保存角色和权限的关系
        for (Integer preId:premissionId){
            RolePremissionKey rolePremissionKey = new RolePremissionKey();
            rolePremissionKey.setPremissionId(preId);
            rolePremissionKey.setRoleId(role.getId());

            rolePremissionKeyMapper.insert(rolePremissionKey);
        }
        logger.info("保存角色{}",role);
    }

    /**
     * 查询所有角色
     *
     * @return java.util.List<com.kaishengit.tms.entity.Role>
     * @date 2018/4/16
     */
    @Override
    public List<Role> findAllRoles() {
        RoleExample rolesExample = new RoleExample();
        return roleMapper.selectByExample(rolesExample);
    }
}
