package com.crm.service;

import com.crm.bean.User;
import com.crm.bean.UserExample;
import com.crm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSerivce {
    @Autowired
    private UserMapper userMapper;

    /*
    *  地球 数据库
    *  人   数据
    *  我要找一个像范冰冰一样漂亮的女人
    *  女的
    *   像范冰冰一样漂亮的
    *   按照范冰冰的模板去找对象
    *
    */
    public User findByNameAndPassword(String username,String password) {
        UserExample example = new UserExample();
        example.createCriteria().andUsrNameEqualTo(username)
                                .andUsrPasswordEqualTo(password);
        List<User> users = userMapper.selectByExample(example);
        if (users != null && users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

}
