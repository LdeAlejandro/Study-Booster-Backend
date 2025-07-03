package com.alejandro.studybooster.module.service;

import com.alejandro.studybooster.module.controller.dto.Question.CreateQuestionDTO;
import com.alejandro.studybooster.module.controller.dto.Question.GetQuestionDTO;
import com.alejandro.studybooster.module.controller.dto.Question.UpdateQuestionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import java.util.Map;

public interface QuestionService {

    public Page<GetQuestionDTO> getAllModuleQuestionsById(Long moduleId, Pageable pageable);
    public ResponseEntity <Map<String, Object>> getModuleQuestionById(Long moduleId, Long questionId);
    public ResponseEntity <Map<String, Object>> createQuestion(Long moduleId, CreateQuestionDTO createQuestionDTO);
    public ResponseEntity <Map<String, Object>> editQuestion(Long moduleId, UpdateQuestionDTO updateQuestionDTO);
    public ResponseEntity <Map<String, Object>> deleteQuestion(Long moduleId, Long questionId);
}
