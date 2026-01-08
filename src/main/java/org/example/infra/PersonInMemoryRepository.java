package org.example.infra;

import org.example.domaine.user.User;
import org.example.domaine.UserRepository;

import java.util.*;

public class PersonInMemoryRepository implements UserRepository {

    private final Map<String, User> storage = new HashMap<>();

    @Override
    public void save(User user) {
        storage.put(user.getId(), user);
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public boolean existsById(String id) {
        return storage.containsKey(id);
    }

    @Override
    public List<User> findAll(){
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<User> findByName(String nom) {
        return storage.values().stream()
                .filter(u -> u.getNom().equals(nom))
                .findFirst();
    }
}
