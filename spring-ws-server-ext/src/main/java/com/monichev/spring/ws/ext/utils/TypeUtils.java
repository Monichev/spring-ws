package com.monichev.spring.ws.ext.utils;

import com.monichev.spring.ws.api_ext.types.TypeExt;

public class TypeUtils {

    public static TypeExt createTypeExt(long id, String name, double seconds) {
        TypeExt result = new TypeExt();
        result.setId(id);
        result.setName(name);
        result.setTimestamp(com.monichev.spring.ws.utils.TypeUtils.createTimestamp(seconds));
        return result;
    }
}
