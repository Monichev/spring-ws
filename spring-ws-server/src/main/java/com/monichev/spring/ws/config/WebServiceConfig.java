package com.monichev.spring.ws.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        return new ServletRegistrationBean<>(servlet, "/spring-ws/*");
    }

    @Bean(name = "common")
    public XsdSchema commonXsd() {
        return new SimpleXsdSchema(new ClassPathResource("/wsdl/common.xsd"));
    }

    @Bean(name = "api-a")
    public Wsdl11Definition apiAWsdl() {
        return new SimpleWsdl11Definition(new ClassPathResource("/wsdl/api-a.wsdl"));
    }

    @Bean(name = "api-a-types")
    public XsdSchema apiAXsd() {
        return new SimpleXsdSchema(new ClassPathResource("/wsdl/api-a-types.xsd"));
    }

    @Bean(name = "api-b")
    public Wsdl11Definition apiBWsdl() {
        return new SimpleWsdl11Definition(new ClassPathResource("/wsdl/api-b.wsdl"));
    }

    @Bean(name = "api-b-types")
    public XsdSchema apiBXsd() {
        return new SimpleXsdSchema(new ClassPathResource("/wsdl/api-b-types.xsd"));
    }
}
