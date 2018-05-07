package com.kaishengit.tms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.tms.controller.exception.ServiceException;
import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.Ticket;
import com.kaishengit.tms.entity.TicketInRecord;
import com.kaishengit.tms.entity.TicketInRecordExample;
import com.kaishengit.tms.mapper.TicketInRecordMapper;
import com.kaishengit.tms.mapper.TicketMapper;
import com.kaishengit.tms.service.TicketService;
import com.kaishengit.tms.util.shiro.ShiroUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private static final Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);
    @Autowired
    private TicketInRecordMapper ticketInRecordMapper;
    @Autowired
    private ShiroUtil shiroUtil;
    @Autowired
    private TicketMapper ticketMapper;

    /**
     *
     *
     * @param pageNo
     * @return
     */
    @Override
    public PageInfo<TicketInRecord> findTicketRecordByPageNo(Integer pageNo) {
        PageHelper.startPage(pageNo,10);

        TicketInRecordExample ticketInRecordExample = new TicketInRecordExample();
        ticketInRecordExample.setOrderByClause("id desc");

        List<TicketInRecord> ticketInRecordList = ticketInRecordMapper.selectByExample(ticketInRecordExample);
        return new PageInfo<>(ticketInRecordList);
    }

    /**
     *
     *
     * @param ticketInRecord
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveTicketInRecord(TicketInRecord ticketInRecord) {
        BigInteger start = new BigInteger(ticketInRecord.getBeginTicketNum());
        BigInteger end = new BigInteger(ticketInRecord.getEndTicketNum());

        if (start.compareTo(end) >= 0){
            throw new ServiceException("");
        }

        //
        List<TicketInRecord> ticketInRecordList = ticketInRecordMapper.selectByExample(new TicketInRecordExample());

        for (TicketInRecord record: ticketInRecordList){
            BigInteger recordStart = new BigInteger(ticketInRecord.getBeginTicketNum());
            BigInteger recordEnd = new BigInteger(ticketInRecord.getEndTicketNum());
            boolean flag =  (recordStart.compareTo(start) <= 0 && recordEnd.compareTo(start) >= 0) || (recordStart.compareTo(end) <= 0 && recordEnd.compareTo(end) >= 0);

            if (flag){
                throw new ServiceException("");
            }
        }
        //
        ticketInRecord.setCreateTime(new Date());
        //
        BigInteger totalNum = end.subtract(start).add(new BigInteger(String.valueOf(1)));
        ticketInRecord.setTotalNum(totalNum.intValue());
        //
        Account account = shiroUtil.getCurrentAccount();
        ticketInRecord.setAccountId(account.getId());

        ticketInRecord.setAccountName(account.getAccountName());

        //
        ticketInRecord.setContent(ticketInRecord.getBeginTicketNum()+"-"+ticketInRecord.getEndTicketNum());
        ticketInRecordMapper.insertSelective(ticketInRecord);

        logger.info("{}",ticketInRecord,account);

        //
        List<Ticket> ticketList = new ArrayList<>();

        for (int i = 0; i<totalNum.intValue();i++){

            Ticket ticket = new Ticket();
            ticket.setCreateTime(new Date());
            ticket.setTicketInTime(new Date());

            ticket.setTicketNum(start.add(new BigInteger(String.valueOf(i))).toString());
            ticket.setTicketState(Ticket.TICKET_STATE_IN_STORE);
            ticketList.add(ticket);
        }
        //
        ticketMapper.batchInsert(ticketList);
    }
}
