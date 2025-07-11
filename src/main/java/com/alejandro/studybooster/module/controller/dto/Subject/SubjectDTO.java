package com.alejandro.studybooster.module.controller.dto.Subject;

import jakarta.validation.constraints.NotBlank;

public record SubjectDTO(

        @NotBlank
        String subjectName

) {
}
