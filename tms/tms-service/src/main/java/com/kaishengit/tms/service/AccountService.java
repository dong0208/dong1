package com.kaishengit.tms.service;

import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.AccountLoginLog;

import java.util.List;
import java.util.Map;

public interface AccountService {


/**
 *
 * @date 2018/4/16
 * @param requestParam
 * @return java.lang.Object
 */

    List<Account> findAllAccountWithRolesByQueryParam(Map<String, Object> requestParam);

/**
 *
 * @date 2018/4/16
 * @param account, rolesIds
 * @return void  
 */ 
    void saveAccount(Account account, Integer[] rolesIds);

    /**
     *
     * @param userMobile
     * @return
     */
    Account findByMobile(String userMobile);

    /**
     *
     * @param accountLoginLog
     */
    void saveAccountLoginLog(AccountLoginLog accountLoginLog);

    /**
     *
     * @param id
     * @return
     */
    Account findAccountById(Integer id);

    /**
     *
     * @param account
     * @param rolesIds
     */
    void updateAccount(Account account, Integer[] rolesIds);
}
