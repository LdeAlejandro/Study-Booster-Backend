package com.alejandro.studybooster.module.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface QuestionOptionsService {

    public ResponseEntity<Map<String, Object>> getAnswerOptions();
    public ResponseEntity<Map<String, Object>> createOption();
    public ResponseEntity<Map<String, Object>> editOption();
    public ResponseEntity<Map<String, Object>> deleteOption();
}
