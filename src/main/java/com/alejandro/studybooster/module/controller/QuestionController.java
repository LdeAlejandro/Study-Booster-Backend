package com.alejandro.studybooster.module.controller;

import com.alejandro.studybooster.module.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/module/{id}/question/")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // get all module questions
    @GetMapping("/module-questions")
    public ResponseEntity getModuleQuestions(){
        return questionService.getModuleQuestions();
    }

    // get specific question by id
    @GetMapping("/{id}")
    public ResponseEntity getModuleQuestions(){
        return questionService.getModuleQuestionById();
    }

    @PostMapping("/create-question")
    public ResponseEntity createQuestion(){
        return questionService.createQuestion();
    }

    @PutMapping("/edit-question/{id}")
    @Transactional
    public ResponseEntity createQuestion(){
        return questionService.editQuestion();
    }

    @DeleteMapping("/delete-question/id")
    @Transactional
    public ResponseEntity deleteQuestion(){
        return questionService.deleteQuestion();
    }


}
