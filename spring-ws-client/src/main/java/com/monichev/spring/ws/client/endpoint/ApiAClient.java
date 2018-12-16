package com.monichev.spring.ws.client.endpoint;

import com.monichev.spring.ws.api_a.types.AddTypeA;
import com.monichev.spring.ws.api_a.types.AddTypeAResponse;
import com.monichev.spring.ws.api_a.types.GetTypeAList;
import com.monichev.spring.ws.api_a.types.GetTypeAListResponse;
import com.monichev.spring.ws.api_a.types.RemoveTypeA;
import com.monichev.spring.ws.api_a.types.RemoveTypeAResponse;
import com.monichev.spring.ws.api_a.types.TypeA;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class ApiAClient extends WebServiceGatewaySupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiAClient.class);

    public GetTypeAListResponse getTypeAList() {
        LOGGER.info("getTypeAList");

        GetTypeAList request = new GetTypeAList();

        GetTypeAListResponse response = (GetTypeAListResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/spring-ws/api-a/GetTypeAList", request,
                        new SoapActionCallback("http://ws.spring.monichev.com/api-a/types/getTypeAListResponse"));

        return response;
    }

    public AddTypeAResponse addTypeA(TypeA typeA) {
        LOGGER.info("addTypeA");

        AddTypeA request = new AddTypeA();
        request.setTypeA(typeA);

        AddTypeAResponse response = (AddTypeAResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/spring-ws/api-a/AddTypeA", request,
                        new SoapActionCallback("http://ws.spring.monichev.com/api-a/types/addTypeAResponse"));

        return response;
    }

    public RemoveTypeAResponse removeTypeA(long id) {
        LOGGER.info("removeTypeA");

        RemoveTypeA request = new RemoveTypeA();
        request.setId(id);

        RemoveTypeAResponse response = (RemoveTypeAResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/spring-ws/api-a/RemoveTypeA", request,
                        new SoapActionCallback("http://ws.spring.monichev.com/api-a/types/removeTypeAResponse"));

        return response;
    }
}
