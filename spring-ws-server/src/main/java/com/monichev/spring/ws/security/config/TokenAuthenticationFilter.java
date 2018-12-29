package com.monichev.spring.ws.security.config;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

class TokenAuthenticationFilter extends GenericFilterBean implements InitializingBean {

    private static final String AUTH_TYPE = "MyToken";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            Authentication authentication = createAuthentication((HttpServletRequest) request);
            if (authentication == null) {
                if (logger.isTraceEnabled()) {
                    logger.trace("Skip request without " + AUTH_TYPE + " authorization");
                }
                filterChain.doFilter(request, response);
                return;
            }
            SecurityContextHolder.getContext().setAuthentication(authentication);
            if (logger.isDebugEnabled()) {
                logger.debug("Populated SecurityContextHolder with custom token: '" + SecurityContextHolder.getContext().getAuthentication() + "'");
            }
        } else if (logger.isDebugEnabled()) {
            logger.debug("SecurityContextHolder not populated with custom token, as it already contained: '"
                    + SecurityContextHolder.getContext().getAuthentication() + "'");
        }
        filterChain.doFilter(request, response);
    }

    private Authentication createAuthentication(HttpServletRequest httpServletRequest) {
        String authParam = Optional.ofNullable(httpServletRequest.getHeader(AUTHORIZATION))
                                   .orElse(httpServletRequest.getParameter("t"));
        if (authParam == null) {
            if (logger.isTraceEnabled()) {
                logger.trace("Can't find request \'Authorization\' header or \'t\' parameter");
            }
            return null;
        }
        String[] authToken = authParam.split(" ");
        if (authToken.length != 2) {
            if (logger.isTraceEnabled()) {
                logger.trace("Unsupported authentication token format. Expected: <type> <credentials>");
            }
            return null;
        }
        String tokenType = authToken[0];
        String token = authToken[1];
        if (!tokenType.equals(AUTH_TYPE)) {
            if (logger.isTraceEnabled()) {
                logger.trace("Unsupported authentication token type. Expected: " + AUTH_TYPE + " got: " + tokenType);
            }
            return null;
        }
        return new TokenAuthenticationToken(token);
    }
}
