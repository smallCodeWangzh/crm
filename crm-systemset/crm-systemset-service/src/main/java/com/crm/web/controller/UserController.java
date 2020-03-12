package com.crm.web.controller;

import com.crm.bean.User;
import com.crm.service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserSerivce serivce;

   /*
    服务内部调用返回具体的对象
    返回给前端 ResponseEnty
    */
   @PostMapping("/findByNameAndPassword")
    public User findByNameAndPassword(String username,String password) {
        return serivce.findByNameAndPassword(username,password);
    }
}
