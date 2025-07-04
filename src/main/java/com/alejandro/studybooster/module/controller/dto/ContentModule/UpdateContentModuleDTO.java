package com.alejandro.studybooster.module.controller.dto.ContentModule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record UpdateContentModuleDTO (

        @NotBlank
        String name

){
}
