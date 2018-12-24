package com.monichev.spring.ws.security.controller;

import java.security.Principal;

import com.monichev.spring.ws.security.entity.User;
import com.monichev.spring.ws.security.service.UserAuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final UserAuthenticationService authentication;

    @Autowired
    public LoginController(@NonNull UserAuthenticationService authentication) {
        this.authentication = authentication;
    }

    @PostMapping("/login")
    String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam(value = "level", required = false) Integer level) {
        return authentication
                .login(new User(username, password, level))
                .orElseThrow(() -> new RuntimeException("invalid login and/or password"));
    }

    @PostMapping("/logout")
    public void logout(Principal principal) {
        if (principal instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken userToken = (UsernamePasswordAuthenticationToken) principal;
            User user = (User) userToken.getPrincipal();
            authentication.logout(user);
        } else {
            throw new IllegalStateException("Unsupported principal type");
        }
    }
}
