package com.monichev.spring.ws.security.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.monichev.spring.ws.security.entity.User;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public final class UUIDAuthenticationService implements UserAuthenticationService {
    private final Map<User, String> userSessions = new HashMap<>();

    private final HashMap<User, String> users = new HashMap<>();

    {
        users.put(new User("user", null, 0), "password");
        users.put(new User("user", null, 1), "password");
        users.put(new User("admin", null, 0), "password");
        users.put(new User("admin", null, 1), "password");
        users.put(new User("admin", null, 2), "password");
    }

    @Override
    public Optional<String> login(User user) {
        String password = users.get(user);
        if (password == null || !password.equals(user.getPassword())) {
            return Optional.empty();
        }
        String session = userSessions.get(user);
        if (session != null) {
            return Optional.of(session);
        }
        final String uuid = UUID.randomUUID().toString();
        userSessions.put(user, uuid);
        return Optional.of(uuid);
    }

    @Override
    public Optional<User> getUser(final String session) {
        return userSessions.entrySet().stream().filter(it -> it.getValue().equals(session)).map(Map.Entry::getKey).findFirst();
    }

    @Override
    public void logout(final User user) {
        String remove = userSessions.remove(user);
        if (remove == null) {
            throw new IllegalStateException("can't find session for user " + user);
        }
    }

    @Override
    public Optional<String> getSession(User user) {
        return Optional.of(userSessions.get(user));
    }
}
