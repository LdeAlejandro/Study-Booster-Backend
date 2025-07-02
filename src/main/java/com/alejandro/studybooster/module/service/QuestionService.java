package com.alejandro.studybooster.module.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface QuestionService {

    public ResponseEntity <Map<String, Object>> getModuleQuestions();
    public ResponseEntity <Map<String, Object>> getModuleQuestionById();
    public ResponseEntity <Map<String, Object>> createQuestion();
    public ResponseEntity <Map<String, Object>> editQuestion();
}
