package org.example.resource.mappers;

import org.example.application.dto.UserDto;
import org.example.resource.response.UserResponse;
import org.example.resource.response.UsersResponse;

import java.util.List;

public class UserResponMapper {

    public UserResponse toResponse(UserDto dto) {
        return new UserResponse(dto.nom(), dto.age());
    }

    public UsersResponse toResponse(List <UserDto> Dtos ) {
         List<UserResponse> users = Dtos.stream().map(this::toResponse).toList();
         return new UsersResponse(users);
    }
}
