package com.alejandro.studybooster.module.controller.dto.QuestionOption;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GetQuestionOptionDTO(

        @NotNull
        Long id,

        @NotBlank
        String option,

        @NotNull
        boolean correct
) {
}
