package com.monichev.spring.ws.client.config;

import com.monichev.spring.ws.client.endpoint.ApiBClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ApiBConfiguration {

    @Bean
    public Jaxb2Marshaller marshallerB() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in pom.xml
        marshaller.setContextPath("com.monichev.spring.ws.api_b.types");
        return marshaller;
    }

    @Bean
    public ApiBClient countryClient(Jaxb2Marshaller marshallerB) {
        ApiBClient client = new ApiBClient();
        client.setDefaultUri("http://localhost:8080/spring-ws");
        client.setMarshaller(marshallerB);
        client.setUnmarshaller(marshallerB);
        return client;
    }
}
