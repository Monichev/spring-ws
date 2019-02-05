package com.monichev.spring.ws.service;

import static com.monichev.spring.ws.utils.TypeUtils.createTypeB;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.monichev.spring.ws.api_b.ApiB;
import com.monichev.spring.ws.api_b.types.TypeB;
import com.monichev.spring.ws.utils.TypeUtils;

import org.springframework.stereotype.Service;

import io.swagger.annotations.Api;

@Service
@Api("/api-b")
@Path("/api-b")
public class ApiBService implements ApiB, ApiService {

    private final List<TypeB> typeBList = Arrays.asList(
            createTypeB(1, "1", 1.1),
            createTypeB(2, "2", 2.2),
            createTypeB(3, "3", 3.3)
    );

    @GET
    @Path("/getTypeBList")
    @Override
    public List<TypeB> getTypeBList() {
        return typeBList;
    }
}
