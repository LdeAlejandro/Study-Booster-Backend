package com.alejandro.studybooster.module.controller;

import com.alejandro.studybooster.module.controller.dto.Question.CreateQuestionDTO;
import com.alejandro.studybooster.module.controller.dto.Question.GetQuestionDTO;
import com.alejandro.studybooster.module.controller.dto.Question.UpdateQuestionDTO;
import com.alejandro.studybooster.module.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subject/{subjectId}/module/{moduleId}/question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // get all module questions
    @GetMapping
    public Page <GetQuestionDTO> getModuleQuestions(
            @PathVariable ("subjectId") Long subjectId,
            @PathVariable ("moduleId") Long moduleId,
            Pageable pageable){

        return questionService.getAllModuleQuestionsById(subjectId, moduleId, pageable);

    }

    // get specific module question by id
    @GetMapping("/{questionId}")
    public ResponseEntity getModuleQuestionById(
            @PathVariable ("subjectId") Long subjectId,
            @PathVariable ("moduleId") Long moduleId,
            @PathVariable ("questionId") Long questionId){

        return questionService.getModuleQuestionById(subjectId, moduleId, questionId);

    }

    // create question
    @PostMapping
    public ResponseEntity createQuestion(
            @PathVariable ("subjectId") Long subjectId,
            @PathVariable ("moduleId") Long moduleId,
            @Valid @RequestBody CreateQuestionDTO createQuestionDTO){

        return questionService.createQuestion(subjectId, moduleId, createQuestionDTO);

    }

}
