package com.alejandro.studybooster.module.controller.dto.Doc;

import jakarta.validation.constraints.NotBlank;

public record UpdateDocDTO(

        @NotBlank
        String title,

        String content
) {
}
