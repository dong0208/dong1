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
    private AccountRoleMapper accountRoleMapper;
    /*
     *
     * @date 2018/4/12
     * @param [accountMobile, password, requstIp]
     * @return �����¼�ɹ����򷵻�Account���������¼ʧ�ܷ���null
     * @throws ServiceException �����¼ʧ�ܣ���ͨ���쳣�׳�����Ĵ���ԭ��
     */
    public Account login(String accountMobile, String password, String requstIp) throws ServiceException {
        //�����ֻ�������Ҷ�Ӧ���˺�
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andAccountMobileEqualTo(accountMobile);
        List<Account> accountList = accountMapper.selectByExample(accountExample);
        Account account = null;
        if (accountList != null && !accountList.isEmpty()) {
            account = accountList.get(0);
            //ƥ������
            if (account.getAccountPassword().equals(DigestUtils.md5Hex(password))) {
                //�ж�״̬
                if (account.STATE_NORMAL.equals(account.getAccountState())) {
                    //��ӵ�¼����־
                    AccountLoginLog accountLoginLog = new AccountLoginLog();
                    accountLoginLog.setAccountId(account.getId());
                    accountLoginLog.setLoginIp(requstIp);
                    accountLoginLog.setLoginTime(new Date());
                    accountLoginLogMapper.insertSelective(accountLoginLog);
                    logger.info("{}��¼ϵͳ", account);
                    return account;
                } else if (Account.STATE_LOCKED.equals(account.getAccountState())) {
                    throw new ServiceException("�˺ű�����");
                } else {
                    throw new ServiceException("�˺ű�����");
                }
            } else {
                throw new ServiceException("�˺Ż��������");
            }
        } else {
            throw new ServiceException("�˺Ż��������");
        }

    }

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
            password = account.getAccountMobile().substring(6);
        }
        //���������MD5����
        password = DigestUtils.md5Hex(password);

        account.setAccountPassword(password);

        //�˺�Ĭ��״̬
        account.setAccountState(Account.STATE_NORMAL);
        accountMapper.insertSelective(account);

        //����˺źͽ�ɫ�Ķ�Ӧ��ϵ��
        for(Integer roleId : rolesIds) {
            AccountRoleKey accountRolesKey = new AccountRoleKey();
            accountRolesKey.setAccountId(account.getId());
            accountRolesKey.setRoleId(roleId);

            accountRoleMapper.insert(accountRolesKey);
        }
    }


}


