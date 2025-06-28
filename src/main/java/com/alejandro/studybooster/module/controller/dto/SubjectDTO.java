package com.alejandro.studybooster.module.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record SubjectDTO(

        @NotBlank
        String subjectName

) {
}
