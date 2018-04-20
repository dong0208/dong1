package com.kaishengit.tms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * ticket_store
 * @author 
 */
public class TicketStore implements Serializable {
    /**
     * 售票点表
     */
    private Integer id;

    private String storeName;

    private String storeManager;

    private String storeTel;

    private String storeAddress;

    private String storeGeoLogitude;

    private String storeGeoLatitude;

    private String storeAttachment;

    private String storeManagerAttachment;

    private Date createTime;

    private Date updateTime;

    private Integer storeAccountId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreManager() {
        return storeManager;
    }

    public void setStoreManager(String storeManager) {
        this.storeManager = storeManager;
    }

    public String getStoreTel() {
        return storeTel;
    }

    public void setStoreTel(String storeTel) {
        this.storeTel = storeTel;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStoreGeoLogitude() {
        return storeGeoLogitude;
    }

    public void setStoreGeoLogitude(String storeGeoLogitude) {
        this.storeGeoLogitude = storeGeoLogitude;
    }

    public String getStoreGeoLatitude() {
        return storeGeoLatitude;
    }

    public void setStoreGeoLatitude(String storeGeoLatitude) {
        this.storeGeoLatitude = storeGeoLatitude;
    }

    public String getStoreAttachment() {
        return storeAttachment;
    }

    public void setStoreAttachment(String storeAttachment) {
        this.storeAttachment = storeAttachment;
    }

    public String getStoreManagerAttachment() {
        return storeManagerAttachment;
    }

    public void setStoreManagerAttachment(String storeManagerAttachment) {
        this.storeManagerAttachment = storeManagerAttachment;
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

    public Integer getStoreAccountId() {
        return storeAccountId;
    }

    public void setStoreAccountId(Integer storeAccountId) {
        this.storeAccountId = storeAccountId;
    }
}