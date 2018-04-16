package com.kaishengit.tms.service;

import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface AccountService {
    /*  
     *ϵͳ��¼
     * @date 2018/4/12
     * @param [accountMobile,�ֻ�����
      * password,����
       * requstIp ��¼��IP
     * @return com.kaishengit.tms.entity.Account  
     */ 
    Account login(String accountMobile, String password, String requstIp)throws ServiceException;
/**
 *gen��ǰ�˴����Ĳ���������ѯ�����˺Ų����ض�Ӧ�Ľ�ɫ�б�
 * @date 2018/4/16
 * @param requestParam
 * @return java.lang.Object
 */
    List<Account> findAllAccountWithRolesByQueryParam(Map<String, Object> requestParam);
/**
 *�����˺�
 * @date 2018/4/16
 * @param account, rolesIds
 * @return void  
 */ 
    void saveAccount(Account account, Integer[] rolesIds);
}
