package com.alejandro.studybooster.module.controller.dto.Question;


import com.alejandro.studybooster.module.controller.dto.QuestionOption.GetQuestionOptionDTO;
import com.alejandro.studybooster.module.entity.Question;
import jakarta.validation.Valid;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record GetQuestionDTO(

        @NotNull
        Long id,

        @NotNull
        String question,

        @NotNull
        String answerExplanation,

        @NotEmpty
        List<@Valid GetQuestionOptionDTO> options


) {
}
