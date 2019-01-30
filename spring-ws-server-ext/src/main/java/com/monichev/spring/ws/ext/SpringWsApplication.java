package com.monichev.spring.ws.ext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.monichev.spring.ws")
@SpringBootApplication
public class SpringWsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWsApplication.class, args);
    }
}
