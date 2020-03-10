package com.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CrmRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrmRegistryApplication.class, args);
    }

}
