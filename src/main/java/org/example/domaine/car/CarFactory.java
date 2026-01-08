package org.example.domaine.car;

import java.util.UUID;

public class CarFactory {

    public Car creat(String mark, String model, int year, String engine) {
        String id = UUID.randomUUID().toString();
        return new Car(id, mark, model, year, engine);
    }
}
