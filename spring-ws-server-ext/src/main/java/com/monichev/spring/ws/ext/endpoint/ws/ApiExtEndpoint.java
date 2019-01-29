package com.monichev.spring.ws.ext.endpoint.ws;

import com.monichev.spring.ws.ext.service.ApiExtService;
import com.monichev.spring.ws.api_ext.types.GetTypeExtList;
import com.monichev.spring.ws.api_ext.types.GetTypeExtListResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
class ApiExtEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiExtEndpoint.class);

    private final ApiExtService apiExtService;

    @Autowired
    public ApiExtEndpoint(ApiExtService apiExtService) {
        this.apiExtService = apiExtService;
    }

    @PayloadRoot(namespace = "http://ws.spring.monichev.com/api-ext/types", localPart = "getTypeExtList")
    @ResponsePayload
    public GetTypeExtListResponse getTypeExtList(@RequestPayload GetTypeExtList request) {
        LOGGER.debug("getTypeExtList");
        GetTypeExtListResponse response = new GetTypeExtListResponse();
        response.getTypeExtList().addAll(apiExtService.getTypeExtList());
        return response;
    }
}
