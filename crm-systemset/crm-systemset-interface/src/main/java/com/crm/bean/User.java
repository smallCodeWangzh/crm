package com.crm.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * sys_user
 * @author 
 */
@Data
public class User implements Serializable {
    /**
     * 编号
     */
    private Long usrId;

    /**
     * 用户名
     */
    private String usrName;

    /**
     * 用户密码
     */
    private String usrPassword;

    /**
     * 用户状态
     */
    private Integer usrFlag;

    /**
     * 角色等级
     */
    private Long usrRoleId;

    private static final long serialVersionUID = 1L;
}