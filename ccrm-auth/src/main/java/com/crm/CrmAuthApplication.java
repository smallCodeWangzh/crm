package com.crm;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2Doc
@EnableFeignClients
public class CrmAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrmAuthApplication.class, args);
    }

}
