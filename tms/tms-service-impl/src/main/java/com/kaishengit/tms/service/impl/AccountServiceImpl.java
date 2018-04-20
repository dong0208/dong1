package com.kaishengit.tms.service.impl;

import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.AccountExample;
import com.kaishengit.tms.entity.AccountLoginLog;

import com.kaishengit.tms.entity.AccountRolesKey;

import com.kaishengit.tms.mapper.AccountLoginLogMapper;
import com.kaishengit.tms.mapper.AccountMapper;
import com.kaishengit.tms.mapper.AccountRolesMapper;
import com.kaishengit.tms.service.AccountService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


/*
 *ϵͳ�˺ŵ�ҵ����
 * @author ���  
 * @date 2018/4/12
 */
@Service
public class AccountServiceImpl implements AccountService {
    public static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountLoginLogMapper accountLoginLogMapper;
    @Autowired
    private AccountRolesMapper accountRolesMapper;

    /**
     * gen��ǰ�˴����Ĳ���������ѯ�����˺Ų����ض�Ӧ�Ľ�ɫ�б�
     *
     * @param requestParam
     * @return java.lang.Object
     * @date 2018/4/16
     */
    @Override
    public List<Account> findAllAccountWithRolesByQueryParam(Map<String, Object> requestParam) {
        return accountMapper.findAllWithRolesByQueryParam(requestParam);
    }


    /**
     * �����˺�
     *
     * @param account
     * @param rolesIds
     * @return void
     * @date 2018/4/16
     */
    @Override
    public void saveAccount(Account account, Integer[] rolesIds) {
        account.setCreateTime(new Date());
        //�˺�Ĭ������Ϊ�ֻ�����ĺ�6λ
        String password;
        if(account.getAccountMobile().length() <= 6) {
            password = account.getAccountMobile();
        } else {
            password = account.getAccountMobile().substring(account.getAccountMobile().length()-6,account.getAccountMobile().length());

        }
        //���������MD5����
        password = DigestUtils.md5Hex(password);

        account.setAccountPassword(password);

        //�˺�Ĭ��״̬
        account.setAccountState(Account.STATE_NORMAL);
        accountMapper.insertSelective(account);

        //����˺źͽ�ɫ�Ķ�Ӧ��ϵ��
        for(Integer roleId : rolesIds) {
            AccountRolesKey accountRolesKey = new AccountRolesKey();
            accountRolesKey.setAccountId(account.getId());
            accountRolesKey.setRolesId(roleId);

            accountRolesMapper.insert(accountRolesKey);
        }
    }

    /**
     * �����ֻ��Ų��ҵ�¼����
     *
     * @param userMobile
     * @return
     */
    @Override
    public Account findByMobile(String userMobile) {
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andAccountMobileEqualTo(userMobile);

        List<Account> accountList = accountMapper.selectByExample(accountExample);
        if (accountList != null &&!accountList.isEmpty()){
            return accountList.get(0);
        }
        return null;
    }

    /**
     * ������־
     *
     * @param accountLoginLog ��¼��־����
     */
    @Override
    public void saveAccountLoginLog(AccountLoginLog accountLoginLog) {
        accountLoginLogMapper.insertSelective(accountLoginLog);
    }


}


