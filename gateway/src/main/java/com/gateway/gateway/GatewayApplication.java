package com.gateway.gateway;

import filter.AccessFilter;
import filter.AccessFilter1;
import filter.AccessFilter2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public AccessFilter accessFilter(){
        return new AccessFilter();
    }

    @Bean
    public AccessFilter1 accessFilter1(){
        return new AccessFilter1();
    }

    @Bean
    public AccessFilter2 accessFilter2(){
        return new AccessFilter2();
    }

}

