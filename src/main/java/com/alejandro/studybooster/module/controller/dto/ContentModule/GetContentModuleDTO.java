package com.alejandro.studybooster.module.controller.dto.ContentModule;

import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record GetContentModuleDTO(

        @NotNull
        Long id,

        @NotNull
        Long subjectId,

        Long parentId,
        Set<Long> childrenIds,
        Set<Long> questionIds,
        Set<Long> docIds
) {
}
