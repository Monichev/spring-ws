package com.monichev.spring.ws.config;

import java.util.Collections;
import java.util.List;

import com.monichev.spring.ws.service.ApiService;

import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RestServiceConfig {
    @Bean
    public Server restServer(List<ApiService> apiServiceList, Bus bus) {
        JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
        endpoint.setBus(bus);
        endpoint.setAddress("/spring-rs");
        endpoint.setServiceBeans((List<Object>)(List<?>) apiServiceList);
        endpoint.setFeatures(Collections.singletonList(new Swagger2Feature()));
        return endpoint.create();
    }
}
