package com.monichev.spring.ws.service;

import static com.monichev.spring.ws.utils.TypeUtils.createTypeA;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.monichev.spring.ws.api_a.ApiA;
import com.monichev.spring.ws.api_a.types.TypeA;
import com.monichev.spring.ws.utils.TypeUtils;

import org.springframework.stereotype.Service;

import io.swagger.annotations.Api;

@Service
@Api("/api-a")
@Path("/api-a")
public class ApiAService implements ApiA, ApiService {

    private final Map<Long, TypeA> typeAList = new LinkedHashMap<>();

    {
        typeAList.put(1L, createTypeA(1, "1", 1.1));
        typeAList.put(2L, createTypeA(2, "2", 2.2));
        typeAList.put(3L, createTypeA(3, "3", 3.3));
    }

    @GET
    @Path("/getTypeAList")
    @Override
    public List<TypeA> getTypeAList() {
        return new ArrayList<>(typeAList.values());
    }

    @POST
    @Path("/addTypeA")
    @Override
    public void addTypeA(TypeA typeA) {
        if (typeAList.containsKey(typeA.getId())) {
            throw new IllegalArgumentException("TypeA already exists with id " + typeA.getId());
        }
        typeAList.put(typeA.getId(), typeA);
    }

    @POST
    @Path("/removeTypeA/{id}")
    @Override
    public TypeA removeTypeA(long id) {
        return typeAList.remove(id);
    }
}
