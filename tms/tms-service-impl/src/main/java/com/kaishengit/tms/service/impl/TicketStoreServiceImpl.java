package com.kaishengit.tms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.tms.entity.StoreAccount;
import com.kaishengit.tms.entity.TicketStore;
import com.kaishengit.tms.entity.TicketStoreExample;
import com.kaishengit.tms.mapper.StoreAccountMapper;
import com.kaishengit.tms.mapper.TicketStoreMapper;
import com.kaishengit.tms.service.TicketStoreService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *年票售票点业务
 * @author
 */
@Service
public class TicketStoreServiceImpl implements TicketStoreService {

   Logger logger = LoggerFactory.getLogger(TicketStoreServiceImpl.class);
    @Autowired
    private TicketStoreMapper ticketStoreMapper;
    @Autowired
    private StoreAccountMapper storeAccountMapper;

    /**
     *创建新的售票点
     * @param ticketStore
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveNewTicktStore(TicketStore ticketStore) {
        ticketStore.setCreateTime(new Date());
        ticketStoreMapper.insertSelective(ticketStore);

        //创建售票点账号
        StoreAccount storeAccount = new StoreAccount();
        storeAccount.setId(ticketStore.getId());
        storeAccount.setTicketStoreId(storeAccount.getId());
        storeAccount.setStoreAccount(ticketStore.getStoreTel());
        //默认密码为手机号的后6位
        storeAccount.setStorePassword(DigestUtils.md5Hex(ticketStore.getStoreTel().substring(5)));
        storeAccount.setCreateTime(new Date());
        storeAccount.setStoreState(StoreAccount.ACCOUNT_STATE_NORMAL);

        storeAccountMapper.insertSelective(storeAccount);
    }

    /**
     *
     *根据当前页面和查询参数查询售票点
     * @param pageNo
     * @param queryParam
     * @return
     */
    @Override
    public PageInfo<TicketStore> findAllTicketStoreByPage(Integer pageNo, Map<String, Object> queryParam) {
        PageHelper.startPage(pageNo,15);

        String storeName = (String)queryParam.get("storeName");
        String storeManager = (String) queryParam.get("storeManager");
        String storeTel = (String) queryParam.get("storeTel");

        TicketStoreExample ticketStoreExample = new TicketStoreExample();
        TicketStoreExample.Criteria criteria = ticketStoreExample.createCriteria();
        if(StringUtils.isNotEmpty(storeName)) {
            criteria.andStoreNameLike("%"+storeName+"%");
        }
        if(StringUtils.isNotEmpty(storeManager)) {
            criteria.andStoreManagerLike("%"+storeManager+"%");
        }
        if(StringUtils.isNotEmpty(storeTel)) {
            criteria.andStoreTelEqualTo(storeTel);
        }
        ticketStoreExample.setOrderByClause("id desc");

        List<TicketStore> ticketStoreList = ticketStoreMapper.selectByExample(ticketStoreExample);
        return new PageInfo<>(ticketStoreList);
    }

    /**
     *根据id查询对应的售票点
     *
     * @param id
     * @return
     */
    @Override
    public TicketStore findTicketStoreById(Integer id) {
        return ticketStoreMapper.selectByPrimaryKey(id);
    }

    /**
     *
     *根据id查询售票点账号
     * @param id
     * @return
     */
    @Override
    public StoreAccount findStoreAccountById(Integer id) {
        return storeAccountMapper.selectByPrimaryKey(id);
    }

    /**
     *
     *修改售票点
     * @param ticketStore
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateTicketStore(TicketStore ticketStore) {
        ticketStore.setUpdateTime(new Date());

        //判断是否修改了联系电话
        StoreAccount storeAccount = storeAccountMapper.selectByPrimaryKey(ticketStore.getId());
        if(!ticketStore.getStoreTel().equals(storeAccount.getStoreAccount())) {
            //如果修改了电话，则同步修改账号
            storeAccount.setStoreAccount(ticketStore.getStoreTel());
            //重设密码
            storeAccount.setStorePassword(DigestUtils.md5Hex(ticketStore.getStoreTel().substring(5)));
            storeAccount.setUpdateTime(new Date());

            storeAccountMapper.updateByPrimaryKeySelective(storeAccount);
        }

        ticketStoreMapper.updateByPrimaryKeySelective(ticketStore);
    }

    /**
     *
     *根据售票年的id删除售票点和售票点账号
     * @param id
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delAccountStore(Integer id) {
       TicketStore ticketStore = ticketStoreMapper.selectByPrimaryKey(id);
       ticketStoreMapper.deleteByPrimaryKey(id);

       storeAccountMapper.deleteByPrimaryKey(id);
        logger.info("删除成功 {}",ticketStore);
    }

    /**
     * 根据售票点ID修改售票点账户的状态
     *
     * @param id
     */
    @Override
    public void editStoreAccountStatusById(Integer id) {
        TicketStore ticketStore = ticketStoreMapper.selectByPrimaryKey(id);

        StoreAccount storeAccount = storeAccountMapper.selectByPrimaryKey(ticketStore.getStoreAccountId());

        storeAccount.setStoreState(StoreAccount.ACCOUNT_STATE_DISABLE);
        storeAccountMapper.updateByPrimaryKeySelective(storeAccount);
    }

    /**
     * 根据售票点ID查询售票点账户
     *
     * @param id
     * @return
     */
    @Override
    public StoreAccount findStoreAccountByTicketStoreId(Integer id) {
        return storeAccountMapper.selectByPrimaryKey(id);
    }

}