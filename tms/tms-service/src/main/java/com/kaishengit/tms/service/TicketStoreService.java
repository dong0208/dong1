package com.kaishengit.tms.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.tms.entity.StoreAccount;
import com.kaishengit.tms.entity.TicketStore;

import java.util.Map;

/**
 * ��Ʊ��Ʊ��ҵ����
 * @author fankay
 */
public interface TicketStoreService {
    /**
     * �����µ���Ʊ��
     * @param ticketStore
     */
    void saveNewTicktStore(TicketStore ticketStore);

    /**
     * ���ݵ�ǰҳ��Ͳ�ѯ������ѯ���۵�
     * @param pageNo
     * @param queryParam
     * @return
     */
    PageInfo<TicketStore> findAllTicketStoreByPage(Integer pageNo, Map<String, Object> queryParam);

    /**
     * ����ID���Ҷ�Ӧ����Ʊ��
     * @param id
     * @return
     */
    TicketStore findTicketStoreById(Integer id);

    /**
     * ��������������Ʊ���˺Ŷ���
     * @param id
     * @return
     */
    StoreAccount findStoreAccountById(Integer id);

    /**
     * �޸���Ʊ�����
     * @param ticketStore
     */
    void updateTicketStore(TicketStore ticketStore);
}