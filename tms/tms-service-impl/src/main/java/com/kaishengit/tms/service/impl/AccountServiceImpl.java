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
 *系统账号的业务类
 * @author 杨冬冬  
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
     * gen据前端传来的参数，来查询所有账号并加载对应的角色列表
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
     * 新增账号
     *
     * @param account
     * @param rolesIds
     * @return void
     * @date 2018/4/16
     */
    @Override
    public void saveAccount(Account account, Integer[] rolesIds) {
        account.setCreateTime(new Date());
        //账号默认密码为手机号码的后6位
        String password;
        if(account.getAccountMobile().length() <= 6) {
            password = account.getAccountMobile();
        } else {
            password = account.getAccountMobile().substring(account.getAccountMobile().length()-6,account.getAccountMobile().length());

        }
        //对密码进行MD5加密
        password = DigestUtils.md5Hex(password);

        account.setAccountPassword(password);

        //账号默认状态
        account.setAccountState(Account.STATE_NORMAL);
        accountMapper.insertSelective(account);

        //添加账号和角色的对应关系表
        for(Integer roleId : rolesIds) {
            AccountRolesKey accountRolesKey = new AccountRolesKey();
            accountRolesKey.setAccountId(account.getId());
            accountRolesKey.setRolesId(roleId);

            accountRolesMapper.insert(accountRolesKey);
        }
    }

    /**
     * 根据手机号查找登录对象
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
     * 保存日志
     *
     * @param accountLoginLog 登录日志对象
     */
    @Override
    public void saveAccountLoginLog(AccountLoginLog accountLoginLog) {
        accountLoginLogMapper.insertSelective(accountLoginLog);
    }


}


