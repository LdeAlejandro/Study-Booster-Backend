package com.alejandro.studybooster.module.controller.dto.ContentModule;

import com.alejandro.studybooster.module.controller.dto.Doc.GetDocsDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record GetContentModuleDTO(

        @NotNull
        Long id,

        @NotBlank
        String name,

        @NotNull
        Long subjectId,

        Long parentId,
        Set<Long> childrenIds,
        Set<Long> questionIds,
        Set<GetDocsDTO> docs
) {
}
