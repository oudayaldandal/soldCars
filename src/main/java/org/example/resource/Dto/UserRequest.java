package org.example.resource.Dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequest(

        @NotBlank(message = "Le nom est obligatoire")
        String nom,

        @NotNull(message = "L'âge est obligatoire")
        @Min(0) @Max(100)
        Integer age,

        @NotBlank(message = "Le mot de passe est obligatoire")
        String rawPassword
) {}

