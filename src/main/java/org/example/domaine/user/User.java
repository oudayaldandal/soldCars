package org.example.domaine.user;

import java.util.Objects;

public class User {

    private final String id;
    private String nom;
    private int age;
    private final String passwordHash;

    public User(String id, String nom, int age, String passwordHash) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Id is null or empty");
        }
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("Name is null or empty");
        }
        if (age < 0 || age > 100) {
            throw new IllegalArgumentException("Age must be between 0 and 100");
        }
        if (passwordHash == null || passwordHash.isBlank()) throw new IllegalArgumentException();

        this.id = id;
        this.nom = nom;
        this.age = age;
        this.passwordHash = passwordHash;
    }

    // getters (lecture contrôlée)
    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getAge() {
        return age;
    }
    public String getPasswordHash() {
        return passwordHash;
    }

    // comportement métier (exemple)
    public void changerAge(int nouvelAge) {
        if (nouvelAge < 0 || nouvelAge > 100) {
            throw new IllegalArgumentException("Age must be between 0 and 100");
        }
        this.age = nouvelAge;
    }

    public boolean checkPassword(String rawPassword) {
        return Objects.equals(passwordHash, rawPassword); // ⚠️ temporaire (pas hashé)
    }

    // identité DDD = id uniquement
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id.equals(user.id);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
