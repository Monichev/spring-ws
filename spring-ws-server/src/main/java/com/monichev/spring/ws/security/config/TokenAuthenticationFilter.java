package com.monichev.spring.ws.security.config;

import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

final class TokenAuthenticationFilter extends GenericFilterBean implements InitializingBean {
    private static final String AUTH_TYPE = "MyToken";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            SecurityContextHolder.getContext().setAuthentication(createAuthentication((HttpServletRequest) request));
            if (logger.isDebugEnabled()) {
                logger.debug("Populated SecurityContextHolder with custom token: '" + SecurityContextHolder.getContext().getAuthentication() + "'");
            }
        } else if (logger.isDebugEnabled()) {
            logger.debug("SecurityContextHolder not populated with custom token, as it already contained: '"
                    + SecurityContextHolder.getContext().getAuthentication() + "'");
        }
        filterChain.doFilter(request, response);
    }

    private Authentication createAuthentication(HttpServletRequest request) {
        final String param = ofNullable(request.getHeader(AUTHORIZATION)).orElse(request.getParameter("t"));
        if (param == null) {
            throw new BadCredentialsException("Authentication token required");
        }
        String[] authToken = param.split(" ");
        if (authToken.length != 2) {
            throw new BadCredentialsException("Unsupported authentication token format. Expected: <type> <credentials>");
        }
        String tokenType = authToken[0];
        String token = authToken[1];
        if (!tokenType.equals(AUTH_TYPE)) {
            throw new BadCredentialsException("Unsupported authentication token type. Expected: " + AUTH_TYPE + " got: " + tokenType);
        }
        return new UsernamePasswordAuthenticationToken(token, null);
    }
}
