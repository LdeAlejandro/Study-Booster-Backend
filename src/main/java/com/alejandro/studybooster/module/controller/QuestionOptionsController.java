package com.alejandro.studybooster.module.controller;

import com.alejandro.studybooster.module.service.QuestionOptionsService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question/{id}/option")
public class QuestionOptionsController {

    private final QuestionOptionsService questionOptionsService;

    public QuestionOptionsController(QuestionOptionsService questionOptionsService){
        this.questionOptionsService = questionOptionsService;
    }

    @GetMapping("/answer-options")
    public ResponseEntity getAnswerOptions(){
        return questionOptionsService.getAnswerOptions();
    }

    @PostMapping("/create-option")
    public ResponseEntity createOption(){
        return questionOptionsService.createOption();
    }

    @PutMapping("/edit-options")
    @Transactional
    public ResponseEntity editOption(){
        return questionOptionsService.editOption();
    }

    @DeleteMapping("/delete-option/{id}")
    @Transactional
    public ResponseEntity deleteOption(){
        return questionOptionsService.deleteOption();
    }


}
