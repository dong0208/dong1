package com.kaishengit.tms.service;

import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface AccountService {
    /*  
     *系统登录
     * @date 2018/4/12
     * @param [accountMobile,手机号码
      * password,密码
       * requstIp 登录的IP
     * @return com.kaishengit.tms.entity.Account  
     */ 
    Account login(String accountMobile, String password, String requstIp)throws ServiceException;
/**
 *gen据前端传来的参数，来查询所有账号并加载对应的角色列表
 * @date 2018/4/16
 * @param requestParam
 * @return java.lang.Object
 */
    List<Account> findAllAccountWithRolesByQueryParam(Map<String, Object> requestParam);
/**
 *新增账号
 * @date 2018/4/16
 * @param account, rolesIds
 * @return void  
 */ 
    void saveAccount(Account account, Integer[] rolesIds);
}
