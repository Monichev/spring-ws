package com.monichev.spring.ws.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceException;

import com.monichev.spring.ws.api_a.ApiA;
import com.monichev.spring.ws.api_a.types.TypeA;
import com.monichev.spring.ws.api_b.ApiB;
import com.monichev.spring.ws.api_b.types.TypeB;
import com.monichev.spring.ws.client.util.TypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws MalformedURLException {
        apiA();
        apiB();
    }

    private static void apiA() throws MalformedURLException {
        LOGGER.info("connecting to api-a");
        URL url = new URL("http://localhost:8080/spring-ws/api-a.wsdl");
        QName qname = new QName("http://ws.spring.monichev.com/api-a", "ApiAImpl");
        Service service = Service.create(url, qname);
        ApiA apiA = service.getPort(ApiA.class);

        LOGGER.info("getTypeAList");
        List<TypeA> typeAList = apiA.getTypeAList();
        typeAList.forEach(it -> LOGGER.info(TypeConverter.toString(it)));

        LOGGER.info("addTypeA duplicate");
        TypeA typeA = new TypeA();
        typeA.setId(1);
        try {
            apiA.addTypeA(typeA);
        } catch (WebServiceException e) {
            LOGGER.info(e.toString());
        }
        LOGGER.info("addTypeA valid");
        typeA.setId(10);
        apiA.addTypeA(typeA);
        TypeA typeAResult = apiA.removeTypeA(10);
        LOGGER.info("remove ID 10 result: " + TypeConverter.toString(typeAResult));
        typeAResult = apiA.removeTypeA(10);
        LOGGER.info("remove ID 10 result: " + TypeConverter.toString(typeAResult));

        LOGGER.info("typeAList");
        typeAList.forEach(it -> LOGGER.info(TypeConverter.toString(it)));
    }

    private static void apiB() throws MalformedURLException {
        LOGGER.info("connecting to api-b");
        URL url = new URL("http://localhost:8080/spring-ws/api-b.wsdl");
        QName qname = new QName("http://ws.spring.monichev.com/api-b", "ApiBImpl");
        Service service = Service.create(url, qname);
        ApiB apiB = service.getPort(ApiB.class);

        LOGGER.info("getTypeBList");
        List<TypeB> typeBList = apiB.getTypeBList();
        typeBList.forEach(it -> LOGGER.info(TypeConverter.toString(it)));
    }
}
