package com.monichev.spring.ws.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.monichev.spring.ws.api_a.ApiA;
import com.monichev.spring.ws.api_a.types.TypeA;
import com.monichev.spring.ws.api_b.ApiB;
import com.monichev.spring.ws.api_b.types.TypeB;
import com.monichev.spring.ws.client.util.TypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws MalformedURLException {
        getTypeAList();
        getTypeBList();
    }

    private static void getTypeAList() throws MalformedURLException {
        LOGGER.info("getTypeAList");
        URL url = new URL("http://localhost:8080/spring-ws/api-a.wsdl");
        QName qname = new QName("http://ws.spring.monichev.com/api-a", "ApiAImpl");
        Service service = Service.create(url, qname);
        ApiA apiA = service.getPort(ApiA.class);

        List<TypeA> typeAList = apiA.getTypeAList();
        typeAList.forEach(it -> LOGGER.info(TypeConverter.toString(it)));
    }

    private static void getTypeBList() throws MalformedURLException {
        LOGGER.info("getTypeBList");
        URL url = new URL("http://localhost:8080/spring-ws/api-b.wsdl");
        QName qname = new QName("http://ws.spring.monichev.com/api-b", "ApiBImpl");
        Service service = Service.create(url, qname);
        ApiB apiB = service.getPort(ApiB.class);

        List<TypeB> typeBList = apiB.getTypeBList();
        typeBList.forEach(it -> LOGGER.info(TypeConverter.toString(it)));
    }
}
