package com.kaishengit.tms.service;


import com.github.pagehelper.PageInfo;
import com.kaishengit.tms.entity.TicketInRecord;

public interface TicketService {

    /**
     * 根据当前页号查询入库记录列表
     * @param pageNo
     * @return
     */
    PageInfo<TicketInRecord> findTicketRecordByPageNo(Integer pageNo);

    /**
     * 新增年票入库记录
     * @param ticketInRecord
     */
    void saveTicketInRecord(TicketInRecord ticketInRecord);
}
