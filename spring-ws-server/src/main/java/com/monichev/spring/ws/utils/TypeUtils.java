package com.monichev.spring.ws.utils;

import com.monichev.spring.ws.api_a.types.TypeA;
import com.monichev.spring.ws.api_b.types.TypeB;
import com.monichev.spring.ws.common.types.Timestamp;

public class TypeUtils {

    public static Timestamp createTimestamp(double seconds) {
        Timestamp timestamp = new Timestamp();
        timestamp.setSeconds((long) seconds);
        timestamp.setMicros((int) ((seconds - timestamp.getSeconds()) * 1e6));
        return timestamp;
    }

    public static TypeA createTypeA(long id, String name, double seconds) {
        TypeA result = new TypeA();
        result.setId(id);
        result.setName(name);
        result.setTimestamp(TypeUtils.createTimestamp(seconds));
        return result;
    }

    public static TypeB createTypeB(long id, String name, double seconds) {
        TypeB result = new TypeB();
        result.setId(id);
        result.setName(name);
        result.setTimestamp(TypeUtils.createTimestamp(seconds));
        return result;
    }
}
