package com.kaishengit.tms.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.tms.entity.StoreAccount;
import com.kaishengit.tms.entity.TicketStore;

import java.util.Map;

/**
 *
 * @author fankay
 */
public interface TicketStoreService {

    /**
     *创建新的售票点
     * @param ticketStore
     */
    void saveNewTicktStore(TicketStore ticketStore);


    /**
     *
     *根据当前页面和查询参数查询售票点
     * @param pageNo
     * @param queryParam
     * @return
     */
    PageInfo<TicketStore> findAllTicketStoreByPage(Integer pageNo, Map<String, Object> queryParam);
    /**
     *根据id查询对应的售票点
     *
     * @param id
     * @return
     */
    TicketStore findTicketStoreById(Integer id);

    /**
     *
     *根据id查询售票点账号
     * @param id
     * @return
     */
    StoreAccount findStoreAccountById(Integer id);

    /**
     *
     *修改售票点
     * @param ticketStore
     */
    void updateTicketStore(TicketStore ticketStore);


    /**
     *
     *根据售票年的id删除售票点和售票点账号
     * @param id
     */
    void delAccountStore(Integer id);

    /**
     * 根据售票点ID修改售票点账户的状态
     * @param id
     */
    void editStoreAccountStatusById(Integer id);

    /**
     * 根据售票点ID查询售票点账户
     * @param id
     * @return
     */
    StoreAccount findStoreAccountByTicketStoreId(Integer id);
}