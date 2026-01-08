package org.example.bootstrap;

import org.example.application.PasswordHasher;
import org.example.application.ServiceApplication;
import org.example.domaine.CarRepository;
import org.example.domaine.car.CarFactory;
import org.example.domaine.user.UserFactory;
import org.example.domaine.UserRepository;
import org.example.infra.security.BCryptPasswordHasher;
import org.example.infra.CarInMemoryRepository;
import org.example.infra.PersonInMemoryRepository;

public class ApplicationConfig {

    private static final UserRepository USER_REPOSITORY = new PersonInMemoryRepository();
    private static final CarRepository carRepository = new CarInMemoryRepository();

    private static final UserFactory USER_FACTORY = new UserFactory();
    private static final CarFactory carFactory = new CarFactory();
    private static final PasswordHasher passwordHasher = new BCryptPasswordHasher();

    private static final ServiceApplication serviceApplication =
            new ServiceApplication(
                    USER_FACTORY,
                    USER_REPOSITORY,
                    carFactory,
                    carRepository,
                    passwordHasher
            );

    public static ServiceApplication serviceApplication() {
        return serviceApplication;
    }
}
