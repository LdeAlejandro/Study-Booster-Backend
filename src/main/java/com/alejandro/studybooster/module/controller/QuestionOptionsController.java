package com.alejandro.studybooster.module.controller;

import com.alejandro.studybooster.module.controller.dto.QuestionOption.CreateQuestionOptionDTO;
import com.alejandro.studybooster.module.controller.dto.QuestionOption.UpdateQuestionOptionDTO;
import com.alejandro.studybooster.module.service.QuestionOptionsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question/{questionId}/option")
public class QuestionOptionsController {

    private final QuestionOptionsService questionOptionsService;

    public QuestionOptionsController(QuestionOptionsService questionOptionsService){
        this.questionOptionsService = questionOptionsService;
    }

    //Get question answer options
    @GetMapping
    public List<CreateQuestionOptionDTO> getAnswerOptions(
            @PathVariable ("questionId") Long questionId){

        return questionOptionsService.getAnswerOptionsByQuestionId(questionId);

    }

    // Create question option
    @PostMapping
    public ResponseEntity createOption(
            @PathVariable ("questionId") Long questionId,
            @Valid @RequestBody CreateQuestionOptionDTO createQuestionOptionDTO){

        return questionOptionsService.createOption(questionId, createQuestionOptionDTO);

    }

    // Edit question option
    @PutMapping("/{optionId}")
    @Transactional
    public ResponseEntity editOption
            (@PathVariable ("questionId") Long questionId,
             @PathVariable ("optionId") Long optionId,
             @Valid @RequestBody UpdateQuestionOptionDTO updateQuestionOptionDTO){

        return questionOptionsService.editOption(questionId, optionId, updateQuestionOptionDTO);

    }

    // Delete question option
    @DeleteMapping("/{optionId}")
    @Transactional
    public ResponseEntity deleteOption(
            @PathVariable ("questionId") Long questionId,
            @PathVariable ("optionId") Long optionId){

        return questionOptionsService.deleteOption(questionId, optionId);

    }


}
