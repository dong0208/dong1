package com.kaishengit.tms.shiro;

import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.AccountLoginLog;
import com.kaishengit.tms.entity.Permission;
import com.kaishengit.tms.entity.Roles;
import com.kaishengit.tms.service.AccountService;
import com.kaishengit.tms.service.RolePermissionService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private RolePermissionService rolePermissionService;

    /**
     * �жϽ�ɫ��Ȩ��
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //��ȡ��ǰ��¼�Ķ���
        Account account = (Account) principalCollection.getPrimaryPrincipal();
        //��ȡ��ǰ��¼����ӵ�еĽ�ɫ
        List<Roles> rolesList = rolePermissionService.findRolesByAccountId(account.getId());
        //��ȡ��ǰ��¼����ӵ�е�Ȩ��
        List<Permission> permissionList = new ArrayList<>();
        for(Roles roles : rolesList) {
            List<Permission> rolesPermissionList = rolePermissionService.findAllPermissionByRolesId(roles.getId());
            permissionList.addAll(rolesPermissionList);
        }

        Set<String> rolesNameSet = new HashSet<>();
        for(Roles roles : rolesList) {
            rolesNameSet.add(roles.getRolesCode());
        }

        Set<String> permissionNameSet = new HashSet<>();
        for(Permission permission : permissionList) {
            permissionNameSet.add(permission.getPermissionCode());
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //��ǰ�û�ӵ�еĽ�ɫ��code��
        simpleAuthorizationInfo.setRoles(rolesNameSet);
        //��ǰ�û�ӵ�е�Ȩ��(code)
        simpleAuthorizationInfo.setStringPermissions(permissionNameSet);
        return simpleAuthorizationInfo;
    }

    /**
     * �жϵ�¼
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String userMobile = usernamePasswordToken.getUsername();
        if(userMobile != null) {
            Account account = accountService.findByMobile(userMobile);
            if(account == null) {
                throw new UnknownAccountException("�Ҳ������˺�:" + userMobile);
            } else {
                if(Account.STATE_NORMAL.equals(account.getAccountState())) {
                    logger.info("{} ��¼�ɹ�: {}",account,usernamePasswordToken.getHost());

                    //�����¼��־
                    AccountLoginLog accountLoginLog = new AccountLoginLog();
                    accountLoginLog.setLoginTime(new Date());
                    accountLoginLog.setLoginIp(usernamePasswordToken.getHost());
                    accountLoginLog.setAccountId(account.getId());
                    accountService.saveAccountLoginLog(accountLoginLog);

                    return new SimpleAuthenticationInfo(account,account.getAccountPassword(),getName());
                } else {
                    throw new LockedAccountException("�˺ű����û�����:" + account.getAccountState());
                }
            }
        }
        return null;
    }
}
