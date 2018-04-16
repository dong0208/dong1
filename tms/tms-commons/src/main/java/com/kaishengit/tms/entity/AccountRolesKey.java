package com.kaishengit.tms.entity;

import java.io.Serializable;

/**
 * account_roles
 * @author 
 */
public class AccountRolesKey implements Serializable {
    private Integer accountId;

    private Integer rolesId;

    private static final long serialVersionUID = 1L;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getRolesId() {
        return rolesId;
    }

    public void setRolesId(Integer rolesId) {
        this.rolesId = rolesId;
    }
}