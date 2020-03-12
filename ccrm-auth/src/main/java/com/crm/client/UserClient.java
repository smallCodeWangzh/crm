package com.crm.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("crm-systemset")
public interface UserClient {
    @PostMapping("/user/findByNameAndPassword")
    Object login(@RequestParam("username") String username, @RequestParam("password") String password);
}
