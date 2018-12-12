package com.monichev.spring.ws.utils;

import com.monichev.spring.ws.common.types.Timestamp;

public class TypeUtils {

    public static Timestamp createTimestamp(double seconds) {
        Timestamp timestamp = new Timestamp();
        timestamp.setSeconds((long) seconds);
        timestamp.setMicros((int) ((seconds - timestamp.getSeconds()) * 1e6));
        return timestamp;
    }
}
