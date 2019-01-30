package com.monichev.spring.ws.ext.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
class WebServiceExtConfig extends WsConfigurerAdapter {

    @Bean(name = "api-ext")
    public Wsdl11Definition apiExtWsdl() {
        return new SimpleWsdl11Definition(new ClassPathResource("/wsdl/api-ext.wsdl"));
    }

    @Bean(name = "api-ext-types")
    public XsdSchema apiExtXsd() {
        return new SimpleXsdSchema(new ClassPathResource("/wsdl/api-ext-types.xsd"));
    }
}
