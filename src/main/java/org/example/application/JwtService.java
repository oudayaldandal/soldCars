package org.example.application;

/**
 * Port (interface) de génération et validation des tokens JWT
 * → utilisé par la couche Application
 */
public interface JwtService {

    String generateToken(String userId);

    String validateAndGetUserId(String token);
}
