package com.alejandro.studybooster.module.service;

import com.alejandro.studybooster.module.controller.dto.QuestionOption.CreateQuestionOptionDTO;
import com.alejandro.studybooster.module.controller.dto.QuestionOption.UpdateQuestionOptionDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.List;

public interface QuestionOptionsService {

    List <CreateQuestionOptionDTO> getAnswerOptionsByQuestionId(Long questionId);
    public ResponseEntity<Map<String, Object>> createOption(Long questionId, CreateQuestionOptionDTO createQuestionOptionDTO);
    public ResponseEntity<Map<String, Object>> editOption(Long questionId, UpdateQuestionOptionDTO updateQuestionOptionDTO);
    public ResponseEntity<Map<String, Object>> deleteOption(Long questionId, Long optionId);
}
