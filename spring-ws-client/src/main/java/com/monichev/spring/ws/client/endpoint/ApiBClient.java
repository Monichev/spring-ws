package com.monichev.spring.ws.client.endpoint;

import com.monichev.spring.ws.api_b.types.GetTypeBList;
import com.monichev.spring.ws.api_b.types.GetTypeBListResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class ApiBClient extends WebServiceGatewaySupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiBClient.class);

    public GetTypeBListResponse getTypeBList() {
        GetTypeBList request = new GetTypeBList();

        LOGGER.info("Requesting TypeB list");

        GetTypeBListResponse response = (GetTypeBListResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/spring-ws/api-b/GetTypeBList", request,
                        new SoapActionCallback("http://ws.spring.monichev.com/api-b/types/getTypeBListResponse"));

        return response;
    }
}
