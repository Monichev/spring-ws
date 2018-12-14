package com.monichev.spring.ws.client.endpoint;

import com.monichev.spring.ws.api_a.types.GetTypeAList;
import com.monichev.spring.ws.api_a.types.GetTypeAListResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class ApiAClient extends WebServiceGatewaySupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiAClient.class);

    public GetTypeAListResponse getTypeAList() {
        GetTypeAList request = new GetTypeAList();

        LOGGER.info("Requesting TypeA list");

        GetTypeAListResponse response = (GetTypeAListResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/spring-ws/api-a/GetTypeAList", request,
                        new SoapActionCallback("http://ws.spring.monichev.com/api-a/types/getTypeAListResponse"));

        return response;
    }
}
