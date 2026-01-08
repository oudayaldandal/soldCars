package org.example.domaine;

import org.example.domaine.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user);

    Optional<User> findById(String id);

    boolean existsById(String id);

    List<User> findAll();

    Optional<User> findByName(String nom);
}
