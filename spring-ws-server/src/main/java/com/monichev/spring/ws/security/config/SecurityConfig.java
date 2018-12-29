package com.monichev.spring.ws.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final RequestMatcher PUBLIC_URLS = new OrRequestMatcher(
            new AntPathRequestMatcher("/login")
    );
    private static final RequestMatcher PROTECTED_URLS = new NegatedRequestMatcher(PUBLIC_URLS);

    private final BasicAuthenticationEntryPoint entryPoint;
    private final BasicAuthenticationProvider basicAuthenticationProvider;
    private final TokenAuthenticationProvider tokenAuthenticationProvider;

    @Autowired
    public SecurityConfig(
            BasicAuthenticationEntryPoint entryPoint,
            BasicAuthenticationProvider basicAuthenticationProvider,
            TokenAuthenticationProvider tokenAuthenticationProvider) {
        this.entryPoint = entryPoint;
        this.basicAuthenticationProvider = basicAuthenticationProvider;
        this.tokenAuthenticationProvider = tokenAuthenticationProvider;
    }

    @Override
    public void configure(final WebSecurity web) {
        web.ignoring().requestMatchers(PUBLIC_URLS);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilterBefore(new TokenAuthenticationFilter(), BasicAuthenticationFilter.class)
            .authorizeRequests()
            .requestMatchers(PROTECTED_URLS).authenticated()
            .and()
            .csrf().disable()
            .formLogin().disable()
            .logout().disable()
            .httpBasic().realmName(BasicAuthenticationEntryPoint.REALM_NAME).authenticationEntryPoint(entryPoint);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(tokenAuthenticationProvider)
            .authenticationProvider(basicAuthenticationProvider);
    }
}
