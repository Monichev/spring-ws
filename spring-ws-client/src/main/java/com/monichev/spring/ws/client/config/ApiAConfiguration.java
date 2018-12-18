package com.monichev.spring.ws.client.config;

import com.monichev.spring.ws.client.endpoint.ApiAClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ApiAConfiguration {

    @Bean
    public Jaxb2Marshaller marshallerA() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.monichev.spring.ws.api_a.types");
        return marshaller;
    }

    @Bean
    public ApiAClient apiAClient(Jaxb2Marshaller marshallerA) {
        ApiAClient client = new ApiAClient();
        client.setDefaultUri("http://localhost:8080/spring-ws");
        client.setMarshaller(marshallerA);
        client.setUnmarshaller(marshallerA);
        return client;
    }
}
