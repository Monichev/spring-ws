package com.monichev.spring.ws.service;

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

    private static TypeB createTypeB(long id, String name, double seconds) {
        TypeB result = new TypeB();
        result.setId(id);
        result.setName(name);
        result.setTimestamp(TypeUtils.createTimestamp(seconds));
        return result;
    }

    @Override
    public List<TypeB> getTypeBList() {
        return typeBList;
    }
}
