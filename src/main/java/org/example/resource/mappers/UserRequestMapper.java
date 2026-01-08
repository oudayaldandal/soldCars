package org.example.resource.mappers;

import org.example.application.dto.UserDto;
import org.example.resource.Dto.UserRequest;

public class UserRequestMapper {

    public UserDto toDto(UserRequest request) {; //// il faut appeller le bycrpt ser ou dans le service
        return new UserDto(
                request.nom(),
                request.age(),
                request.rawPassword()
        );
    }
}
