package com.monichev.spring.ws.security.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.monichev.spring.ws.security.entity.User;

import org.springframework.stereotype.Service;

@Service
public final class UserAuthenticationService {
    private final Map<User, String> userSessions = new HashMap<>();

    public Optional<String> login(User user) {
        // todo perform user credentials check
        Optional<String> existingSession = getExistingSession(user);
        if (existingSession.isPresent()) {
            return existingSession;
        }
        final String uuid = UUID.randomUUID().toString();
        userSessions.put(user, uuid);
        return Optional.of(uuid);
    }

    public Optional<User> findBySession(final String session) {
        return userSessions.entrySet().stream().filter(it -> it.getValue().equals(session)).map(Map.Entry::getKey).findFirst();
    }

    public void logout(final User user) {
        String remove = userSessions.remove(user);
        if (remove == null) {
            throw new IllegalStateException("Can't find session for user " + user);
        }
    }

    private Optional<String> getExistingSession(User user) {
        return userSessions.entrySet().stream().filter(it -> it.getKey().equals(user)).map(Map.Entry::getValue).findAny();
    }
}
