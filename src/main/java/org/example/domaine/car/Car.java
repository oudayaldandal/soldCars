package org.example.domaine.car;

import java.util.Objects;

public class Car {

    private final String id;
    private final String mark;
    private final String model;
    private final int year;
    private final String engine;

    public Car(String id, String mark, String model, int year, String engine) {

        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Car id is null or empty");
        }
        if (mark == null || mark.isBlank()) {
            throw new IllegalArgumentException("Mark is null or empty");
        }
        if (model == null || model.isBlank()) {
            throw new IllegalArgumentException("Model is null or empty");
        }
        if (year < 1886) { // première voiture
            throw new IllegalArgumentException("Invalid car year");
        }
        if (engine == null || engine.isBlank()) {
            throw new IllegalArgumentException("Engine is null or empty");
        }

        this.id = id;
        this.mark = mark;
        this.model = model;
        this.year = year;
        this.engine = engine;
    }

    public String getId() {
        return id;
    }

    public String getMark() {
        return mark;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getEngine() {
        return engine;
    }

    // ===== identité DDD =====
    // Deux voitures sont égales si elles ont le même id

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return id.equals(car.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
