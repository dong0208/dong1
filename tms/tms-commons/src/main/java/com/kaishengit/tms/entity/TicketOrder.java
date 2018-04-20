package com.kaishengit.tms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * ticket_order
 * @author 
 */
public class TicketOrder implements Serializable {
    /**
     * 年票订单
     */
    private Long id;

    /**
     * 订单数量
     */
    private String ticketOrderNum;

    /**
     * 订单价格
     */
    private Long ticketOrderPrice;

    private Date createTime;

    private Date update;

    /**
     * 订单类型
     */
    private String ticketOrderType;

    private Long ticketId;

    private Long customerId;

    private Integer storeAccountId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicketOrderNum() {
        return ticketOrderNum;
    }

    public void setTicketOrderNum(String ticketOrderNum) {
        this.ticketOrderNum = ticketOrderNum;
    }

    public Long getTicketOrderPrice() {
        return ticketOrderPrice;
    }

    public void setTicketOrderPrice(Long ticketOrderPrice) {
        this.ticketOrderPrice = ticketOrderPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }

    public String getTicketOrderType() {
        return ticketOrderType;
    }

    public void setTicketOrderType(String ticketOrderType) {
        this.ticketOrderType = ticketOrderType;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getStoreAccountId() {
        return storeAccountId;
    }

    public void setStoreAccountId(Integer storeAccountId) {
        this.storeAccountId = storeAccountId;
    }
}