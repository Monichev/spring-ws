package com.monichev.spring.ws.security.service;

import java.util.Optional;

import com.monichev.spring.ws.security.entity.User;

import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

public interface UserAuthenticationService {

    Optional<String> login(User user);

    void logout(User user);

    Optional<User> getUser(final String session);

    Optional<String> getSession(User user);
}
