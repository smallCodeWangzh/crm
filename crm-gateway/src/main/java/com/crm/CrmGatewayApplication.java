package com.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class CrmGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrmGatewayApplication.class, args);
    }

}
