package com.monichev.spring.ws.client;

import com.monichev.spring.ws.api_a.types.GetTypeAListResponse;
import com.monichev.spring.ws.api_b.types.GetTypeBListResponse;
import com.monichev.spring.ws.client.endpoint.ApiAClient;
import com.monichev.spring.ws.client.endpoint.ApiBClient;
import com.monichev.spring.ws.client.util.TypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner lookupA(ApiAClient apiAClient) {
        return args -> {
            GetTypeAListResponse response = apiAClient.getTypeAList();
            response.getTypeAList().forEach(it -> LOGGER.info(TypeConverter.toString(it)));
        };
    }

    @Bean
    CommandLineRunner lookupB(ApiBClient apiBClient) {
        return args -> {
            GetTypeBListResponse response = apiBClient.getTypeBList();
            response.getTypeBList().forEach(it -> LOGGER.info(TypeConverter.toString(it)));
        };
    }
}
