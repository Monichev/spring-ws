package com.monichev.spring.ws.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.monichev.spring.ws.api_a.ApiA;
import com.monichev.spring.ws.api_a.types.TypeA;
import com.monichev.spring.ws.utils.TypeUtils;

import org.springframework.stereotype.Service;

@Service
public class ApiAService implements ApiA {

    private final Map<Long, TypeA> typeAList = new LinkedHashMap<>();

    {
        typeAList.put(1L, createTypeA(1, "1", 1.1));
        typeAList.put(2L, createTypeA(2, "2", 2.2));
        typeAList.put(3L, createTypeA(3, "3", 3.3));
    }

    private static TypeA createTypeA(long id, String name, double seconds) {
        TypeA result = new TypeA();
        result.setId(id);
        result.setName(name);
        result.setTimestamp(TypeUtils.createTimestamp(seconds));
        return result;
    }

    @Override
    public List<TypeA> getTypeAList() {
        return new ArrayList<>(typeAList.values());
    }

    @Override
    public void addTypeA(TypeA typeA) {
        if (typeAList.containsKey(typeA.getId())) {
            throw new IllegalArgumentException("TypeA already exists with id " + typeA.getId());
        }
        typeAList.put(typeA.getId(), typeA);
    }

    @Override
    public TypeA removeTypeA(long id) {
        return typeAList.remove(id);
    }
}
