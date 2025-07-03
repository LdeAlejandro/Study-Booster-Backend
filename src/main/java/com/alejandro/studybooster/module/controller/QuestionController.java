//package com.alejandro.studybooster.module.controller;
//
//import com.alejandro.studybooster.module.controller.dto.Question.CreateQuestionDTO;
//import com.alejandro.studybooster.module.controller.dto.Question.UpdateQuestionDTO;
//import com.alejandro.studybooster.module.service.QuestionService;
//import jakarta.validation.Valid;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.ResponseEntity;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/module/{moduleId}/question/")
//public class QuestionController {
//
//    private final QuestionService questionService;
//
//    public QuestionController(QuestionService questionService) {
//        this.questionService = questionService;
//    }
//
//    // get all module questions
//    @GetMapping
//    public Page <CreateQuestionDTO> getModuleQuestions(
//            @PathVariable ("moduleId") Long moduleId,
//            Pageable pageable){
//
//        return questionService.getAllModuleQuestionsById(moduleId, pageable);
//
//    }
//
//    // get specific question by id
//    @GetMapping("/{questionId}")
//    public ResponseEntity getModuleQuestionById(
//            @PathVariable ("moduleId") Long moduleId,
//            @PathVariable ("questionId") Long questionId){
//
//        return questionService.getModuleQuestionById(moduleId, questionId);
//
//    }
//
//    // create question
//    @PostMapping
//    public ResponseEntity createQuestion(
//            @PathVariable ("moduleId") Long moduleId,
//            @Valid @RequestBody CreateQuestionDTO createQuestionDTO){
//
//        return questionService.createQuestion(moduleId, createQuestionDTO);
//
//    }
//
//    // edit question
//    @PutMapping("/{questionId}")
//    @Transactional
//    public ResponseEntity editQuestion(
//            @PathVariable ("moduleId") Long moduleId,
//            @Valid @RequestBody UpdateQuestionDTO updateQuestionDTO){
//
//        return questionService.editQuestion(moduleId, updateQuestionDTO);
//
//    }
//
//    // delete question
//    @DeleteMapping("/{questionId}")
//    @Transactional
//    public ResponseEntity deleteQuestion(
//            @PathVariable ("moduleId") Long moduleId,
//            @PathVariable ("questionId") Long questionId){
//
//        return questionService.deleteQuestion(moduleId, questionId);
//    }
//
//
//}
