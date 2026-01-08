package org.example.resource.Dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank String nom,
        @NotBlank String password
) {}
