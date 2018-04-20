package com.kaishengit.tms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * store_login_log
 * @author 
 */
public class StoreLoginLog implements Serializable {
    private Long id;

    /**
     * 登录ip
     */
    private String loginIp;

    /**
     * 登录时间
     */
    private Date loginTime;

    private Integer storeAccountId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getStoreAccountId() {
        return storeAccountId;
    }

    public void setStoreAccountId(Integer storeAccountId) {
        this.storeAccountId = storeAccountId;
    }
}