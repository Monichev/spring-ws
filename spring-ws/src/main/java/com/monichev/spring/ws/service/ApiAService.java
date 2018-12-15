package com.monichev.spring.ws.service;

import java.util.Arrays;
import java.util.List;

import com.monichev.spring.ws.api_a.ApiA;
import com.monichev.spring.ws.api_a.types.TypeA;
import com.monichev.spring.ws.utils.TypeUtils;

import org.springframework.stereotype.Service;

@Service
public class ApiAService implements ApiA {

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

    @Override
    public List<TypeA> getTypeAList() {
        return typeAList;
    }
}
