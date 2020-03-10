package com.crm.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * sys_role
 * @author 
 */
@Data
public class Role implements Serializable {
    /**
     * 编号
     */
    private Long roleId;

    /**
     * 角色名字
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDesc;

    /**
     * 角色状态
     */
    private Integer roleFlag;

    private static final long serialVersionUID = 1L;
}