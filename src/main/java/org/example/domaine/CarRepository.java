package org.example.domaine;

import org.example.domaine.car.Car;

import java.util.List;
import java.util.Optional;

public interface CarRepository {

    void save(Car car);

    Optional<Car> findById(String id);

    List<Car> findAll();

    boolean existsById(String id);
}
