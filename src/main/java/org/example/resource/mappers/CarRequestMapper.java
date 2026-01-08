package org.example.resource.mappers;

import org.example.application.dto.CarDto;
import org.example.resource.Dto.CarRequest;

public class CarRequestMapper {
    public CarDto toDto(CarRequest request){
        return new CarDto(request.mark(), request.model(), request.year(), request.engine());
    }
}
