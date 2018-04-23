package com.kaishengit.tms.service;


import com.github.pagehelper.PageInfo;
import com.kaishengit.tms.entity.TicketInRecord;

public interface TicketService {

    /**
     * ���ݵ�ǰҳ�Ų�ѯ����¼�б�
     * @param pageNo
     * @return
     */
    PageInfo<TicketInRecord> findTicketRecordByPageNo(Integer pageNo);

    /**
     * ������Ʊ����¼
     * @param ticketInRecord
     */
    void saveTicketInRecord(TicketInRecord ticketInRecord);
}
