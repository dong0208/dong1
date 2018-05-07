package com.kaishengit.tms.shiro;

import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.AccountLoginLog;
import com.kaishengit.tms.entity.Permission;
import com.kaishengit.tms.entity.Roles;
import com.kaishengit.tms.service.AccountService;
import com.kaishengit.tms.service.RolePermissionService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class ShiroRealm extends AuthorizingRealm{

    private Logger logger = LoggerFactory.getLogger(ShiroRealm.class);
    @Autowired
    private AccountService accountService;
    @Autowired
    private RolePermissionService rolePermissionService;

    /**
     * 判断角色和权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //获得当前的登陆对对象
        Account account = (Account) principalCollection.getPrimaryPrincipal();
        //获得当前登录对象拥有的角色
        List<Roles> rolesList = rolePermissionService.findRolesByAccountId(account.getId());
        //获取当前对象拥有的权限
        List<Permission> permissionList = new ArrayList<>();
        for (Roles roles : rolesList){
            List<Permission> rolesPermissionList = rolePermissionService.findAllPermissionByRolesId(roles.getId());
            permissionList.addAll(rolesPermissionList);
        }
        Set <String> rolesNameSet = new HashSet<>();
        for (Roles roles : rolesList){
            rolesNameSet.add(roles.getRolesCode());
        }
        Set <String> permissionNameSet = new HashSet<>();
        for (Permission permission:permissionList){
            permissionNameSet.add(permission.getPermissionCode());
        }

        SimpleAuthorizationInfo simpleAuthenticationInfo = new SimpleAuthorizationInfo();
        //当前对象拥有的角色code
        simpleAuthenticationInfo.setRoles(rolesNameSet);
        //当前对象拥有的权限
        simpleAuthenticationInfo.setStringPermissions(permissionNameSet);
        return simpleAuthenticationInfo;
    }


    /**
     * 判断登陆
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;

        String userMobile = usernamePasswordToken.getUsername();
        if (userMobile != null){
            Account account = accountService.findByMobile(userMobile);
            if (account == null){
                throw new UnknownAccountException("Can't find the account："+userMobile);
            }else{
                if (Account.STATE_NORMAL.equals(account.getAccountState())){
                    logger.info("{} 登录成功:{}",account,usernamePasswordToken.getHost());
                    //保存登陆日志
                    AccountLoginLog accountLoginLog = new AccountLoginLog();
                    accountLoginLog.setAccountId(account.getId());
                    accountLoginLog.setLoginIp(usernamePasswordToken.getHost());
                    accountLoginLog.setLoginTime(new Date());

                    return new SimpleAuthenticationInfo(account,account.getAccountPassword(),getName());
                }else{
                    throw new LockedAccountException("Account is disabled or locked:"+account.getAccountState());
                }
            }
        }
        return null;
    }
}
