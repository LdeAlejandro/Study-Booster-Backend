package com.alejandro.studybooster.module.controller.dto.Doc;

import jakarta.validation.constraints.NotBlank;

public record GetDocDTO(

        Long id,
        String title,
        String content
) {
}
