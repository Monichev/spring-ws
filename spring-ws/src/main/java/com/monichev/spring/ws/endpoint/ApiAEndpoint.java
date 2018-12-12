package com.monichev.spring.ws.endpoint;

import java.util.Arrays;
import java.util.List;

import com.monichev.spring.ws.api_a.types.GetTypeAList;
import com.monichev.spring.ws.api_a.types.GetTypeAListResponse;
import com.monichev.spring.ws.api_a.types.TypeA;
import com.monichev.spring.ws.utils.TypeUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ApiAEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiAEndpoint.class);

    private final List<TypeA> typeAList = Arrays.asList(
            createTypeA(1, "1", 1.1),
            createTypeA(2, "2", 2.2),
            createTypeA(3, "3", 3.3)
    );

    private static TypeA createTypeA(long id, String name, double seconds) {
        TypeA result = new TypeA();
        result.setId(id);
        result.setName(name);
        result.setTimestamp(TypeUtils.createTimestamp(seconds));
        return result;
    }

    @PayloadRoot(namespace = "http://ws.spring.monichev.com/api-a/types", localPart = "getTypeAList")
    @ResponsePayload
    public GetTypeAListResponse getTypeAList(@RequestPayload GetTypeAList request) {
        LOGGER.debug("getTypeAList");
        GetTypeAListResponse response = new GetTypeAListResponse();
        response.getTypeAList().addAll(typeAList);
        return response;
    }
}
