package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement(proxyTargetClass = true)//开启事物管理
@SpringBootApplication
public class SpringRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRedisApplication.class, args);
    }

}
