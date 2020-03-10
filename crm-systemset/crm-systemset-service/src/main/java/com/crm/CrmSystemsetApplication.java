package com.crm;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.crm.mapper")
@EnableSwagger2Doc
public class CrmSystemsetApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrmSystemsetApplication.class,args);
    }
}
