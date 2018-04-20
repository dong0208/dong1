package com.kaishengit.tms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * customer
 * @author 
 */
public class Customer implements Serializable {
    /**
     * 消费者表
     */
    private Long id;

    /**
     * 消费者姓名
     */
    private String customerName;

    /**
     * 消费者身份证件
     */
    private String customerIdCard;

    /**
     * 消费者身份证件照片
     */
    private String customerIdCardPhoto;

    /**
     * 消费者身份证件照片背面
     */
    private String customerIdCardPhotoBack;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新i时间
     */
    private Date updateTime;

    /**
     * 消费者照片
     */
    private String customerPhoto;

    private String customerTel;

    /**
     * 消费者住址
     */
    private String customerAddress;

    /**
     * 消费者年龄
     */
    private Integer customerAge;

    /**
     * 消费者性别
     */
    private String customerGender;

    private Long ticketId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerIdCard() {
        return customerIdCard;
    }

    public void setCustomerIdCard(String customerIdCard) {
        this.customerIdCard = customerIdCard;
    }

    public String getCustomerIdCardPhoto() {
        return customerIdCardPhoto;
    }

    public void setCustomerIdCardPhoto(String customerIdCardPhoto) {
        this.customerIdCardPhoto = customerIdCardPhoto;
    }

    public String getCustomerIdCardPhotoBack() {
        return customerIdCardPhotoBack;
    }

    public void setCustomerIdCardPhotoBack(String customerIdCardPhotoBack) {
        this.customerIdCardPhotoBack = customerIdCardPhotoBack;
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

    public String getCustomerPhoto() {
        return customerPhoto;
    }

    public void setCustomerPhoto(String customerPhoto) {
        this.customerPhoto = customerPhoto;
    }

    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Integer getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(Integer customerAge) {
        this.customerAge = customerAge;
    }

    public String getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(String customerGender) {
        this.customerGender = customerGender;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }
}