package com.monichev.spring.ws.security.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

class TokenAuthenticationToken extends UsernamePasswordAuthenticationToken {

    TokenAuthenticationToken(String token) {
        super(token, null);
    }
}
