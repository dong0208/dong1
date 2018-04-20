package com.kaishengit.tms.service;

import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.AccountLoginLog;
import com.kaishengit.tms.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface AccountService {


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

    /**
     * 根据手机号查找登录对象
     * @param userMobile
     * @return
     */
    Account findByMobile(String userMobile);

    /**
     * 保存日志
     * @param accountLoginLog
     */
    void saveAccountLoginLog(AccountLoginLog accountLoginLog);
}
