package com.monichev.spring.ws.endpoint.ws;

import com.monichev.spring.ws.api_a.types.AddTypeA;
import com.monichev.spring.ws.api_a.types.AddTypeAResponse;
import com.monichev.spring.ws.api_a.types.GetTypeAList;
import com.monichev.spring.ws.api_a.types.GetTypeAListResponse;
import com.monichev.spring.ws.api_a.types.RemoveTypeA;
import com.monichev.spring.ws.api_a.types.RemoveTypeAResponse;
import com.monichev.spring.ws.service.ApiAService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
class ApiAEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiAEndpoint.class);

    private final ApiAService apiAService;

    @Autowired
    public ApiAEndpoint(ApiAService apiAService) {
        this.apiAService = apiAService;
    }

    @PayloadRoot(namespace = "http://ws.spring.monichev.com/api-a/types", localPart = "getTypeAList")
    @ResponsePayload
    public GetTypeAListResponse getTypeAList(@RequestPayload GetTypeAList request) {
        LOGGER.debug("getTypeAList");
        GetTypeAListResponse response = new GetTypeAListResponse();
        response.getTypeAList().addAll(apiAService.getTypeAList());
        return response;
    }

    @PayloadRoot(namespace = "http://ws.spring.monichev.com/api-a/types", localPart = "addTypeA")
    @ResponsePayload
    public AddTypeAResponse addTypeA(@RequestPayload AddTypeA request) {
        LOGGER.debug("addTypeA");
        apiAService.addTypeA(request.getTypeA());
        return new AddTypeAResponse();
    }

    @PayloadRoot(namespace = "http://ws.spring.monichev.com/api-a/types", localPart = "removeTypeA")
    @ResponsePayload
    public RemoveTypeAResponse removeTypeA(@RequestPayload RemoveTypeA request) {
        LOGGER.debug("removeTypeA");
        RemoveTypeAResponse response = new RemoveTypeAResponse();
        response.setRemovedTypeA(apiAService.removeTypeA(request.getId()));
        return response;
    }
}
