package com.kaishengit.tms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * ticket
 * @author 
 */
public class Ticket implements Serializable {
    /**
     * 年票表
     */
    private Long id;

    /**
     * 年票总数
     */
    private String ticketNum;

    /**
     * 年票入库时间
     */
    private Date ticketInTime;

    /**
     * 年票状态
     */
    private String ticketState;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 出库时间
     */
    private Date ticketOutTime;

    /**
     * 年票的开始有效日期
     */
    private Date ticketValidityStart;

    /**
     * 年票的开始有效结束日期
     */
    private Date ticketValidityEnd;

    private Integer storeAccountId;

    private Long customerId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(String ticketNum) {
        this.ticketNum = ticketNum;
    }

    public Date getTicketInTime() {
        return ticketInTime;
    }

    public void setTicketInTime(Date ticketInTime) {
        this.ticketInTime = ticketInTime;
    }

    public String getTicketState() {
        return ticketState;
    }

    public void setTicketState(String ticketState) {
        this.ticketState = ticketState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getTicketOutTime() {
        return ticketOutTime;
    }

    public void setTicketOutTime(Date ticketOutTime) {
        this.ticketOutTime = ticketOutTime;
    }

    public Date getTicketValidityStart() {
        return ticketValidityStart;
    }

    public void setTicketValidityStart(Date ticketValidityStart) {
        this.ticketValidityStart = ticketValidityStart;
    }

    public Date getTicketValidityEnd() {
        return ticketValidityEnd;
    }

    public void setTicketValidityEnd(Date ticketValidityEnd) {
        this.ticketValidityEnd = ticketValidityEnd;
    }

    public Integer getStoreAccountId() {
        return storeAccountId;
    }

    public void setStoreAccountId(Integer storeAccountId) {
        this.storeAccountId = storeAccountId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}