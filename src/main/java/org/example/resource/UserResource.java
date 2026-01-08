package org.example.resource;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.example.application.ServiceApplication;
import org.example.application.dto.CarDto;
import org.example.application.dto.UserDto;
import org.example.resource.Dto.CarRequest;
import org.example.resource.Dto.LoginRequest;
import org.example.resource.Dto.UserRequest;
import org.example.bootstrap.ApplicationConfig;
import org.example.resource.mappers.CarRequestMapper;
import org.example.resource.mappers.UserRequestMapper;
import org.example.resource.mappers.UserResponMapper;
import org.example.resource.response.CreateUserResponse;
import org.example.resource.response.LoginResponse;
import org.example.resource.response.UserResponse;
import org.example.resource.response.UsersResponse;

@Path("/info")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private final ServiceApplication service;
    private final UserRequestMapper userMapper = new UserRequestMapper();
    private  final CarRequestMapper carMapper = new CarRequestMapper();
    private  final UserResponMapper userResponMapper = new UserResponMapper();

    public UserResource() {
        this.service = ApplicationConfig.serviceApplication();
    }

    @POST
    @Path("/signup")
    public Response create(@Valid UserRequest request) {

        // 1. Adapter HTTP → Application
        UserDto dto = userMapper.toDto(request);

        // 2. Appel du service
        String id = service.creatPerson(dto);

        // 3. Réponse HTTP (REST correct)
        return Response
                .status(Response.Status.CREATED)
                .entity(new CreateUserResponse(id))
                .build();
        //bycript commet impkement
    }

    @POST
    @Path("/cars")
    public Response create(@Valid CarRequest request) {

        // 1. Adapter HTTP → Application
        CarDto dto = carMapper.toDto(request);

        // 2. Appel du service
        String id = service.creatCar(dto);

        // 3. Réponse HTTP (REST correct)
        return Response
                .status(Response.Status.CREATED)
                .entity(new CreateUserResponse(id))
                .build();
    }

    @GET
    @Path("/users")
    public Response getUsers() {


        // 2. Appel du service
        var personDto = service.getUsers();

        UsersResponse response = userResponMapper.toResponse(personDto);

        // 3. Réponse HTTP (REST correct)
       return Response
               .ok(response)
               .build();
    }

    @GET
    @Path("/users/{id}")
    public Response getUserById(@PathParam("id") String id) {
        var userDto = service.getUser(id);
        UserResponse response = userResponMapper.toResponse(userDto);
        return Response
                .ok(response)
                .build();
    }

    @POST
    @Path("/login")
    public Response login(@Valid LoginRequest request) {

        String token = service.login(
                request.nom(),
                request.password()
        );

        return Response.ok(new LoginResponse(token)).build();
    }

}
