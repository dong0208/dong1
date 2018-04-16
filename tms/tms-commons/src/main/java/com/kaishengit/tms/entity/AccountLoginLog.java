package com.kaishengit.tms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * account_login_log
 * @author 
 */
public class AccountLoginLog implements Serializable {
    private Long id;

    /**
     * 用户登录ip
     */
    private String loginIp;

    /**
     * 用户登录时间
     */
    private Date loginTime;

    private Integer accountId;

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

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
}