package com.kaishengit.tms.entity;

import java.io.Serializable;

/**
 * role_premission
 * @author 
 */
public class RolePremissionKey implements Serializable {
    private Integer roleId;

    private Integer premissionId;

    private static final long serialVersionUID = 1L;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPremissionId() {
        return premissionId;
    }

    public void setPremissionId(Integer premissionId) {
        this.premissionId = premissionId;
    }
}