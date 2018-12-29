package com.monichev.spring.ws.security.util;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public final class RequestContextUtil {

    private RequestContextUtil() {
    }

    public static Integer getUserDesiredLevel() {
        HttpServletRequest request = getHttpServletRequest();
        return Optional.ofNullable(request.getHeader("Level"))
                       .map(Integer::valueOf)
                       .orElse(0);
    }

    private static HttpServletRequest getHttpServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new IllegalStateException("RequestAttributes should not be null");
        }
        return ((ServletRequestAttributes) requestAttributes).getRequest();
    }
}
