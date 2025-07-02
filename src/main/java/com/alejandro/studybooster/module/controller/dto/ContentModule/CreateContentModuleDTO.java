package com.alejandro.studybooster.module.controller.dto.ContentModule;

import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record CreateContentModuleDTO(

        @NotNull
        Long subjectId,

        @NotNull
        Long parentId

) {
}
