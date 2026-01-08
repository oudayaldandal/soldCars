package org.example.infra;


import org.example.domaine.car.Car;
import org.example.domaine.CarRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CarInMemoryRepository implements CarRepository {

    // stockage en mémoire (thread-safe)
    private final Map<String, Car> storage = new ConcurrentHashMap<>();

    @Override
    public void save(Car car) {
        Objects.requireNonNull(car, "Car cannot be null");
        storage.put(car.getId(), car);
    }

    @Override
    public Optional<Car> findById(String id) {
        if (id == null || id.isBlank()) {
            return Optional.empty();
        }
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Car> findAll() {
        return new ArrayList<>(storage.values());
    }


    @Override
    public boolean existsById(String id) {
        return storage.containsKey(id);
    }
}
