package com.kaishengit.tms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * store_account
 * @author 
 */
public class StoreAccount implements Serializable {
    /**
     * 售票点账户
     */
    private Integer id;

    /**
     * 账户
     */
    private String storeAccount;

    /**
     * 密码
     */
    private String storePassword;

    private Date createTime;

    private Date updateTime;

    /**
     * 售票点状态
     */
    private String storeState;

    private Integer ticketStoreId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStoreAccount() {
        return storeAccount;
    }

    public void setStoreAccount(String storeAccount) {
        this.storeAccount = storeAccount;
    }

    public String getStorePassword() {
        return storePassword;
    }

    public void setStorePassword(String storePassword) {
        this.storePassword = storePassword;
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

    public String getStoreState() {
        return storeState;
    }

    public void setStoreState(String storeState) {
        this.storeState = storeState;
    }

    public Integer getTicketStoreId() {
        return ticketStoreId;
    }

    public void setTicketStoreId(Integer ticketStoreId) {
        this.ticketStoreId = ticketStoreId;
    }
}