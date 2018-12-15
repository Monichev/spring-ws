package com.monichev.spring.ws.endpoint;

import com.monichev.spring.ws.api_b.types.GetTypeBList;
import com.monichev.spring.ws.api_b.types.GetTypeBListResponse;
import com.monichev.spring.ws.service.ApiBService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ApiBEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiBEndpoint.class);

    private final ApiBService apiBService;

    @Autowired
    public ApiBEndpoint(ApiBService apiBService) {
        this.apiBService = apiBService;
    }

    @PayloadRoot(namespace = "http://ws.spring.monichev.com/api-b/types", localPart = "getTypeBList")
    @ResponsePayload
    public GetTypeBListResponse getTypeBList(@RequestPayload GetTypeBList request) {
        LOGGER.debug("getTypeAList");
        GetTypeBListResponse response = new GetTypeBListResponse();
        response.getTypeBList().addAll(apiBService.getTypeBList());
        return response;
    }
}
