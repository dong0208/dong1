package com.kaishengit.tms.service.impl;

import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.AccountExample;
import com.kaishengit.tms.entity.AccountLoginLog;
import com.kaishengit.tms.entity.AccountRoleKey;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.mapper.AccountLoginLogMapper;
import com.kaishengit.tms.mapper.AccountMapper;
import com.kaishengit.tms.mapper.AccountRoleMapper;
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
    private AccountRoleMapper accountRoleMapper;
    /*
     *
     * @date 2018/4/12
     * @param [accountMobile, password, requstIp]
     * @return 如果登录成功，则返回Account对象，如果登录失败返回null
     * @throws ServiceException 如果登录失败，则通过异常抛出具体的错误原因
     */
    public Account login(String accountMobile, String password, String requstIp) throws ServiceException {
        //根据手机号码查找对应的账号
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andAccountMobileEqualTo(accountMobile);
        List<Account> accountList = accountMapper.selectByExample(accountExample);
        Account account = null;
        if (accountList != null && !accountList.isEmpty()) {
            account = accountList.get(0);
            //匹配密码
            if (account.getAccountPassword().equals(DigestUtils.md5Hex(password))) {
                //判断状态
                if (account.STATE_NORMAL.equals(account.getAccountState())) {
                    //添加登录的日志
                    AccountLoginLog accountLoginLog = new AccountLoginLog();
                    accountLoginLog.setAccountId(account.getId());
                    accountLoginLog.setLoginIp(requstIp);
                    accountLoginLog.setLoginTime(new Date());
                    accountLoginLogMapper.insertSelective(accountLoginLog);
                    logger.info("{}登录系统", account);
                    return account;
                } else if (Account.STATE_LOCKED.equals(account.getAccountState())) {
                    throw new ServiceException("账号被锁定");
                } else {
                    throw new ServiceException("账号被禁用");
                }
            } else {
                throw new ServiceException("账号或密码错误");
            }
        } else {
            throw new ServiceException("账号或密码错误");
        }

    }

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
            password = account.getAccountMobile().substring(6);
        }
        //对密码进行MD5加密
        password = DigestUtils.md5Hex(password);

        account.setAccountPassword(password);

        //账号默认状态
        account.setAccountState(Account.STATE_NORMAL);
        accountMapper.insertSelective(account);

        //添加账号和角色的对应关系表
        for(Integer roleId : rolesIds) {
            AccountRoleKey accountRolesKey = new AccountRoleKey();
            accountRolesKey.setAccountId(account.getId());
            accountRolesKey.setRoleId(roleId);

            accountRoleMapper.insert(accountRolesKey);
        }
    }


}


