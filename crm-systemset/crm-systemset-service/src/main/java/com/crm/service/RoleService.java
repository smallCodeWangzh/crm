package com.crm.service;

import com.crm.bean.Role;
import com.crm.bean.RoleExample;
import com.crm.enums.ExceptionEnums;
import com.crm.exception.CrmException;
import com.crm.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public Role findById(Long id){
        if(id == null) {
            throw new CrmException(ExceptionEnums.PARAM_IS_NULL);
        }
        return roleMapper.selectByPrimaryKey(id);
    }

    public List<Role> findAllRole() {
        return roleMapper.selectByExample(new RoleExample());
    }
}
