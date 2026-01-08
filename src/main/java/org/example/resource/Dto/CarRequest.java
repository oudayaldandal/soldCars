package org.example.resource.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CarRequest(

        @NotBlank(message = "Mark must not be blank")
        String mark,

        @NotBlank(message = "Model must not be blank")
        String model,

        @Pattern(
                regexp = "\\d{4}",
                message = "Year must be a 4-digit number"
        )
        int year,

        @NotBlank(message = "Engine must not be blank")
        String engine
) {}
