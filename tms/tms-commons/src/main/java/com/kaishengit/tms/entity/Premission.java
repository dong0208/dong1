package com.kaishengit.tms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * premission
 * @author 
 */
public class Premission implements Serializable {
    public static final String MENU_TYPE = "菜单";
    public static final String BUTTON_TYPE = "按钮";

    /**
     * 权限表
     */
    private Integer id;

    /**
     * 权限名称
     */
    private String premissionName;

    /**
     * 权限代号
     */
    private String premissionCode;

    private String premissionUrl;

    private String premissionType;

    /**
     * 父类id
     */
    private Integer parentId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPremissionName() {
        return premissionName;
    }

    public void setPremissionName(String premissionName) {
        this.premissionName = premissionName;
    }

    public String getPremissionCode() {
        return premissionCode;
    }

    public void setPremissionCode(String premissionCode) {
        this.premissionCode = premissionCode;
    }

    public String getPremissionUrl() {
        return premissionUrl;
    }

    public void setPremissionUrl(String premissionUrl) {
        this.premissionUrl = premissionUrl;
    }

    public String getPremissionType() {
        return premissionType;
    }

    public void setPremissionType(String premissionType) {
        this.premissionType = premissionType;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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
}