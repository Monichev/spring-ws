package com.monichev.spring.ws.config;

import javax.xml.ws.Endpoint;

import com.monichev.spring.ws.service.ApiAService;
import com.monichev.spring.ws.service.ApiBService;

import org.apache.cxf.Bus;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class WebServiceConfig {
    @Bean
    public Endpoint apiA(ApiAService apiAService, Bus bus) {
        EndpointImpl endpoint = new EndpointImpl(bus, apiAService);
        endpoint.getFeatures().add(new LoggingFeature());
        endpoint.publish("/ws/api-a");
        return endpoint;
    }

    @Bean
    public Endpoint apiB(ApiBService apiBService, Bus bus) {
        EndpointImpl endpoint = new EndpointImpl(bus, apiBService);
        endpoint.getFeatures().add(new LoggingFeature());
        endpoint.publish("/ws/api-b");
        return endpoint;
    }
}
