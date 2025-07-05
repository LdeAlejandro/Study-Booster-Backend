package com.alejandro.studybooster.module.controller;

import com.alejandro.studybooster.module.controller.dto.Question.UpdateQuestionDTO;
import com.alejandro.studybooster.module.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/question")
public class AnyModuleQuestionController {


    private final QuestionService questionService;

    public AnyModuleQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // get Any module question by id
    @GetMapping("/{questionId}")
    public ResponseEntity getModuleQuestionById(

            @PathVariable("questionId") Long questionId){

        return questionService.getQuestionById(questionId);

    }

    // update any question by id
    @PutMapping("/{questionId}")
    @Transactional
    public ResponseEntity updateQuestion(
            @PathVariable ("questionId") Long questionId,
            @Valid @RequestBody UpdateQuestionDTO updateQuestionDTO){

        return questionService.updateQuestion(questionId, updateQuestionDTO);

    }



    // delete question
    @DeleteMapping("/{questionId}")
    @Transactional
    public ResponseEntity deleteQuestion(
            @PathVariable ("questionId") Long questionId){

        return questionService.deleteQuestion( questionId);
    }
}
