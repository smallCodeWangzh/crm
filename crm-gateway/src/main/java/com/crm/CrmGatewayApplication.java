package com.crm;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableSwagger2Doc
public class CrmGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrmGatewayApplication.class, args);
    }

}
