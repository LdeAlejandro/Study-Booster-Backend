package com.alejandro.studybooster.module.controller.dto.ContentModule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.Nullable;

import java.util.Set;

public record CreateContentModuleDTO(

        @NotBlank
        String name,

        Long parentId

) {
}
