package com.monichev.spring.ws.client.util;

import com.monichev.spring.ws.api_a.types.TypeA;
import com.monichev.spring.ws.api_b.types.TypeB;
import com.monichev.spring.ws.common.types.Timestamp;

public class TypeConverter {
    public static String toString(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return "Timestamp{seconds=" + timestamp.getSeconds() + ", micros=" + timestamp.getMicros() + "}";
    }

    public static String toString(TypeA typeA) {
        if (typeA == null) {
            return null;
        }
        return "TypeA{id=" + typeA.getId() + ", name=" + typeA.getName() + ", time=" + toString(typeA.getTimestamp()) + "}";
    }

    public static String toString(TypeB typeB) {
        if (typeB == null) {
            return null;
        }
        return "TypeB{id=" + typeB.getId() + ", name=" + typeB.getName() + ", time=" + toString(typeB.getTimestamp()) + "}";
    }
}
