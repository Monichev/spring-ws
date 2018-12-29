package com.monichev.spring.ws.security.config;

import java.util.ArrayList;
import java.util.Optional;

import com.monichev.spring.ws.security.entity.User;
import com.monichev.spring.ws.security.service.UserAuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
class TokenAuthenticationProvider implements AuthenticationProvider {

    private final UserAuthenticationService userAuthenticationService;

    @Autowired
    TokenAuthenticationProvider(UserAuthenticationService userAuthenticationService) {
        this.userAuthenticationService = userAuthenticationService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final Object token = authentication.getPrincipal();
        User user = Optional
                .ofNullable(token)
                .map(String::valueOf)
                .flatMap(userAuthenticationService::getUser)
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find user with authentication token: " + token));
        return new UsernamePasswordAuthenticationToken(user, user.getPassword(), new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(TokenAuthenticationToken.class);
    }
}
