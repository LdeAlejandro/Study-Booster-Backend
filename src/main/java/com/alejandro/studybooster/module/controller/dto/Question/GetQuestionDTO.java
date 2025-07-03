package com.alejandro.studybooster.module.controller.dto.Question;

import com.alejandro.studybooster.module.controller.dto.QuestionOption.CreateQuestionOptionDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record GetQuestionDTO(


        String question,


        String answerExplanation,


        List<@Valid CreateQuestionOptionDTO> options
) {
}
