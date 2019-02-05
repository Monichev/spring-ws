package com.monichev.spring.ws.ext.service;

import static com.monichev.spring.ws.ext.utils.TypeUtils.createTypeExt;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.monichev.spring.ws.api_ext.ApiExt;
import com.monichev.spring.ws.api_ext.types.TypeExt;
import com.monichev.spring.ws.service.ApiService;

import org.springframework.stereotype.Service;

import io.swagger.annotations.Api;

@Service
@Api("/api-ext")
@Path("/api-ext")
public class ApiExtService implements ApiExt, ApiService {

    private final List<TypeExt> typeExtList = Arrays.asList(
            createTypeExt(1, "1", 1.1),
            createTypeExt(2, "2", 2.2),
            createTypeExt(3, "3", 3.3)
    );


    @GET
    @Path("/getTypeExtList")
    @Override
    public List<TypeExt> getTypeExtList() {
        return typeExtList;
    }
}
