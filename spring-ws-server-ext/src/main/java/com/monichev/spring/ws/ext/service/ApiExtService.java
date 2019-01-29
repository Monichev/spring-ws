package com.monichev.spring.ws.ext.service;

import static com.monichev.spring.ws.ext.utils.TypeUtils.createTypeExt;

import java.util.Arrays;
import java.util.List;

import com.monichev.spring.ws.api_ext.ApiExt;
import com.monichev.spring.ws.api_ext.types.TypeExt;

import org.springframework.stereotype.Service;

@Service
public class ApiExtService implements ApiExt {

    private final List<TypeExt> typeExtList = Arrays.asList(
            createTypeExt(1, "1", 1.1),
            createTypeExt(2, "2", 2.2),
            createTypeExt(3, "3", 3.3)
    );

    @Override
    public List<TypeExt> getTypeExtList() {
        return typeExtList;
    }
}
