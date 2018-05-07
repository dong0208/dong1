package com.kaishengit.tms.service;


import com.github.pagehelper.PageInfo;
import com.kaishengit.tms.entity.TicketInRecord;

public interface TicketService {

    /**
     *
     * @param pageNo
     * @return
     */
    PageInfo<TicketInRecord> findTicketRecordByPageNo(Integer pageNo);

    /**
     *
     * @param ticketInRecord
     */
    void saveTicketInRecord(TicketInRecord ticketInRecord);
}
