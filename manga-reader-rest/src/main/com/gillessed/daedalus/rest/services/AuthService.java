package com.gillessed.daedalus.rest.services;

import com.gillessed.daedalus.rest.exception.AuthException;
import com.gillessed.daedalus.rest.model.User;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by gcole on 5/22/16.
 */
public class AuthService {
    private final Map<String, User> users;
    private final Map<String, User> tokens;

    public AuthService() {
        users = new ConcurrentHashMap<>();
        users.put("123", new User("123", "456"));
        tokens = new ConcurrentHashMap<>();
        tokens.put("admin", users.get("123"));
    }

    public String login(String username, String password) throws AuthException {
        User user = users.getOrDefault(username, null);
        if(user == null) {
            throw new AuthException(String.format("Username %s not found.", username));
        }
        if(!user.getPassword().equals(password)) {
            throw new AuthException(String.format("Incorrect password for %s", username));
        }
        return generateToken(user);
    }

    private String generateToken(User user) {
        String newToken = UUID.randomUUID().toString() + UUID.randomUUID().toString();
        newToken = newToken.replace('-','\0');
        tokens.put(newToken, user);
        return newToken;
    }

    public void verifyToken(String token) throws AuthException {
        if(token == null || !tokens.containsKey(token)) {
            throw new AuthException("Invalid auth token. You are not logged in.");
        }
    }
}
