package com.kaishengit.tms.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * account
 * @author 
 */
public class Account implements Serializable {
    public static final String STATE_NORMAL = "正常";
    public static final String STATE_DISABLE = "禁用";
    public static final String STATE_LOCKED = "锁定";
    private Integer id;

    private String accountName;

    private String accountMobile;

    /**
     * 登录密码
     */
    private String accountPassword;

    private Date createTime;

    private Date updateTime;

    public List<Role> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Role> rolesList) {
        this.rolesList = rolesList;
    }

    private List<Role> rolesList;


    /**
     * 状态
     */
    private String accountState;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountMobile() {
        return accountMobile;
    }

    public void setAccountMobile(String accountMobile) {
        this.accountMobile = accountMobile;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
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

    public String getAccountState() {
        return accountState;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountName='" + accountName + '\'' +
                ", accountMobile='" + accountMobile + '\'' +
                ", accountPassword='" + accountPassword + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", accountState='" + accountState + '\'' +
                '}';
    }

    public void setAccountState(String accountState) {
        this.accountState = accountState;
    }
}