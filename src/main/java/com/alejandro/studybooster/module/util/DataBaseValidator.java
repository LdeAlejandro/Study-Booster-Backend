package com.alejandro.studybooster.module.util;


import com.alejandro.studybooster.module.controller.dto.auxiliarsDTO.ContextEntitiesSubjectAndModuleDTO;
import com.alejandro.studybooster.module.controller.dto.auxiliarsDTO.ContextEntitiesSubjectModuleAndQuestionDTO;
import com.alejandro.studybooster.module.entity.ContentModule;
import com.alejandro.studybooster.module.entity.Question;
import com.alejandro.studybooster.module.entity.Subject;
import com.alejandro.studybooster.module.repository.ContentModuleRepository;
import com.alejandro.studybooster.module.repository.QuestionRepository;
import com.alejandro.studybooster.module.repository.SubjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class DataBaseValidator {

    //repositories
    private SubjectRepository subjectRepository;
    private ContentModuleRepository contentModuleRepository;
    private QuestionRepository questionRepository;

    public DataBaseValidator(SubjectRepository subjectRepository, ContentModuleRepository contentModuleRepository, QuestionRepository questionRepository) {
        this.subjectRepository = subjectRepository;
        this.contentModuleRepository = contentModuleRepository;
        this.questionRepository = questionRepository;
    }


    //Validate if subject and module exist
    public Optional<?> validateIfSubjectAndModuleExist(Long subjectId, Long moduleId){

        Map<String, Object> response = new HashMap<>();

        // check if subject id exist
        Optional <Subject> targetSubject = subjectRepository.findById(subjectId);

        if (targetSubject.isEmpty() || !targetSubject.get().getId().equals(subjectId)) {
            response.put("message", "Subject not found");
            return Optional.of(ResponseEntity.status(HttpStatus.NOT_FOUND).body(response));
        }

        // check if module ID belong to subject ID
        Optional<ContentModule> targetModule = contentModuleRepository.findById(moduleId);

        if (targetModule.isEmpty() || !targetModule.get().getSubject().getId().equals(subjectId)) {
            response.put("message", "Module not found for the given subject");
            return Optional.of(ResponseEntity.status(HttpStatus.NOT_FOUND).body(response));
        }

        return Optional.of( new ContextEntitiesSubjectAndModuleDTO(targetSubject.get(), targetModule.get()));
    }

    //Validate if subject and module exist
    public Optional<?> validateIfSubjectModuleAndQuestionExist(Long subjectId, Long moduleId, Long questionid){

        Map<String, Object> response = new HashMap<>();

        // check if subject id exist
        Optional <Subject> targetSubject = subjectRepository.findById(subjectId);

        if (targetSubject.isEmpty() || !targetSubject.get().getId().equals(subjectId)) {
            response.put("message", "Subject not found");
            return Optional.of(ResponseEntity.status(HttpStatus.NOT_FOUND).body(response));
        }

        // check if module ID exist and belong to subject ID
        Optional<ContentModule> targetModule = contentModuleRepository.findById(moduleId);

        if (targetModule.isEmpty() || !targetModule.get().getSubject().getId().equals(subjectId)) {
            response.put("message", "Module not found for the given subject");
            return Optional.of(ResponseEntity.status(HttpStatus.NOT_FOUND).body(response));
        }

        // check if question Id exist and belongs to module Id
        Optional<Question> targetQuestion = questionRepository.findById(questionid);

        if (targetQuestion.isEmpty() || !targetQuestion.get().getId().equals(questionid)) {
            response.put("message", "Question not found for the given module or subject");
            return Optional.of(ResponseEntity.status(HttpStatus.NOT_FOUND).body(response));
        }



        return Optional.of( new ContextEntitiesSubjectModuleAndQuestionDTO(targetSubject.get(), targetModule.get(), targetQuestion.get()));
    }
}
