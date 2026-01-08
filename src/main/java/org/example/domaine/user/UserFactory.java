package org.example.domaine.user;

import java.util.UUID;

public class UserFactory {

    public User create(String nom, int age, String password) {
        String id = UUID.randomUUID().toString();
        return new User(id, nom, age, password);
    }
}
