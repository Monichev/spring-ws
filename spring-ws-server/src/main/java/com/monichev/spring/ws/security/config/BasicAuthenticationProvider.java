package com.monichev.spring.ws.security.config;

import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.monichev.spring.ws.security.entity.User;
import com.monichev.spring.ws.security.service.UserAuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class BasicAuthenticationProvider implements AuthenticationProvider {

    private final UserAuthenticationService authenticationService;

    @Autowired
    public BasicAuthenticationProvider(UserAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    private static Integer getUserDesiredLevel() {
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

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        Integer level = getUserDesiredLevel();
        User user = new User(name, password, level);
        Optional<String> session = authenticationService.login(user);
        if (!session.isPresent()) {
            throw new BadCredentialsException("Access denied");
        }
        return new UsernamePasswordAuthenticationToken(user, password, new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
