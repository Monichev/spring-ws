package com.monichev.spring.ws.service;

import static com.monichev.spring.ws.utils.TypeUtils.createTypeB;

import java.util.Arrays;
import java.util.List;

import com.monichev.spring.ws.api_b.ApiB;
import com.monichev.spring.ws.api_b.types.TypeB;
import com.monichev.spring.ws.utils.TypeUtils;

import org.springframework.stereotype.Service;

@Service
public class ApiBService implements ApiB {

    private final List<TypeB> typeBList = Arrays.asList(
            createTypeB(1, "1", 1.1),
            createTypeB(2, "2", 2.2),
            createTypeB(3, "3", 3.3)
    );

    @Override
    public List<TypeB> getTypeBList() {
        return typeBList;
    }
}
