package org.example.application;

public interface PasswordHasher {

    String hash(String plainPassword);

    boolean matches(String plainPassword, String hashedPassword);
}