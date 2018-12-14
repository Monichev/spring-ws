package com.monichev.spring.ws.client.util;

import com.monichev.spring.ws.api_a.types.TypeA;
import com.monichev.spring.ws.api_b.types.TypeB;
import com.monichev.spring.ws.common.types.Timestamp;

public class TypeConverter {
    public static String toString(Timestamp timestamp) {
        return "Timestamp{seconds=" + timestamp.getSeconds() + ", micros=" + timestamp.getMicros() + "}";
    }

    public static String toString(TypeA typeA) {
        return "TypeA{id=" + typeA.getId() + ", name=" + typeA.getName() + ", time=" + toString(typeA.getTimestamp()) + "}";
    }

    public static String toString(TypeB typeB) {
        return "TypeB{id=" + typeB.getId() + ", name=" + typeB.getName() + ", time=" + toString(typeB.getTimestamp()) + "}";
    }
}
