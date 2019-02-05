package com.monichev.spring.ws.ext.config;

import javax.xml.ws.Endpoint;

import com.monichev.spring.ws.ext.service.ApiExtService;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class WebServiceExtConfig {

    @Bean
    public Endpoint apiExt(ApiExtService apiExtService, SpringBus springBus) {
        EndpointImpl endpoint = new EndpointImpl(springBus, apiExtService);
        endpoint.getFeatures().add(new LoggingFeature());
        endpoint.publish("/api-ext");
        return endpoint;
    }
}
