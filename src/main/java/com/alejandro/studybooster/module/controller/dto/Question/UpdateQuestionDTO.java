package com.alejandro.studybooster.module.controller.dto.Question;


import com.alejandro.studybooster.module.controller.dto.QuestionOption.UpdateQuestionOptionDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UpdateQuestionDTO(

        @NotBlank
        String question,

        @NotNull
        String answerExplanation,

        @NotEmpty
        List<@Valid UpdateQuestionOptionDTO> options
) {
}
