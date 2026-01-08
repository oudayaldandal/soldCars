package org.example.application;

import jakarta.ws.rs.NotFoundException;
import org.example.application.dto.CarDto;
import org.example.application.dto.UserDto;
import org.example.domaine.CarRepository;
import org.example.domaine.car.Car;
import org.example.domaine.car.CarFactory;
import org.example.domaine.user.User;
import org.example.domaine.user.UserFactory;

import org.example.domaine.UserRepository;
import org.example.infra.security.JwtServiceImpl;

import java.util.List;

public class ServiceApplication {
    private final UserFactory userFactory;
    private final UserRepository userRepository;
    private final CarFactory carFactory;
    private final CarRepository carRepository;
    private final JwtService jwtService;
    private final PasswordHasher passwordHasher;

    public ServiceApplication(UserFactory userFactory, UserRepository userRepository, CarFactory carFactory,
                              CarRepository carRepository, PasswordHasher passwordHasher) {
        this.userFactory = userFactory;
        this.userRepository = userRepository;
        this.carFactory = carFactory;
        this.carRepository = carRepository;
        this.jwtService = new JwtServiceImpl();
        this.passwordHasher = passwordHasher;
    }

    public String creatPerson(UserDto dto) {
        String hashedPassword = passwordHasher.hash(dto.rawPassword());
        User user = userFactory.create(dto.nom(), dto.age(), hashedPassword);
        userRepository.save(user);
        return user.getId();
    }

    public String creatCar(CarDto dto) {
        Car car = carFactory.creat(dto.mark(), dto.model(), dto.year(), dto.engine());
        carRepository.save(car);
        return car.getId();
    }

    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDto(
                        user.getNom(),
                        user.getAge(),
                        user.getPasswordHash()
                ))
                .toList();
    }

    public UserDto getUser(String id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("User with id " + id + " not found")
                );

        return new UserDto(user.getNom(), user.getAge(), user.getPasswordHash());
    }


    public String login(String nom, String password) {


        var user = userRepository.findByName(nom)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));


        if(!passwordHasher.matches(password, user.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtService.generateToken(user.getId());
    }

}