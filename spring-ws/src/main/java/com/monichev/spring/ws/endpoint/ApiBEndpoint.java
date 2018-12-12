package com.monichev.spring.ws.endpoint;

import java.util.Arrays;
import java.util.List;

import com.monichev.spring.ws.api_b.types.GetTypeBList;
import com.monichev.spring.ws.api_b.types.GetTypeBListResponse;
import com.monichev.spring.ws.api_b.types.TypeB;
import com.monichev.spring.ws.utils.TypeUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ApiBEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiBEndpoint.class);

    private final List<TypeB> typeBList = Arrays.asList(
            createTypeB(1, "1", 1.1),
            createTypeB(2, "2", 2.2),
            createTypeB(3, "3", 3.3)
    );

    private static TypeB createTypeB(long id, String name, double seconds) {
        TypeB result = new TypeB();
        result.setId(id);
        result.setName(name);
        result.setTimestamp(TypeUtils.createTimestamp(seconds));
        return result;
    }

    @PayloadRoot(namespace = "http://ws.spring.monichev.com/api-b/types", localPart = "getTypeBList")
    @ResponsePayload
    public GetTypeBListResponse getTypeBList(@RequestPayload GetTypeBList request) {
        LOGGER.debug("getTypeAList");
        GetTypeBListResponse response = new GetTypeBListResponse();
        response.getTypeBList().addAll(typeBList);
        return response;
    }
}
