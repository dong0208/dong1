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
 *��ɫ��Ȩ�޵�ҵ����
 * @author ���
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
     * �鿴����Ȩ��
     *
     * @author ���
     * @date 2018/4/13
     */
    @Override
    public List<Premission> findAllPermission() {
        PremissionExample premissionExample = new PremissionExample();
        return premissionMapper.selectByExample(premissionExample);


    }

    /**
     * ����Ȩ�޵�����
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
     * ���Ȩ��
     *
     * @param premission
     * @return void
     * @date 2018/4/13
     */
    @Override
    public void savePression(Premission premission) {
        premission.setCreateTime(new Date());
        premissionMapper.insertSelective(premission);
        logger.info("���Ȩ��{}",premission);
    }

    /**
     * ������ɫ
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
        //�����ɫ��Ȩ�޵Ĺ�ϵ
        for (Integer preId:premissionId){
            RolePremissionKey rolePremissionKey = new RolePremissionKey();
            rolePremissionKey.setPremissionId(preId);
            rolePremissionKey.setRoleId(role.getId());

            rolePremissionKeyMapper.insert(rolePremissionKey);
        }
        logger.info("�����ɫ{}",role);
    }

    /**
     * ��ѯ���н�ɫ
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
