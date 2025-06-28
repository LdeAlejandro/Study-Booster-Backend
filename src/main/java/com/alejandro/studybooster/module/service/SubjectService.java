package com.alejandro.studybooster.module.service;

import com.alejandro.studybooster.module.controller.dto.SubjectDTO;
import com.alejandro.studybooster.module.entity.Subject;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface SubjectService {

    public Page<Subject> getSubjects(@PageableDefault(size = 10, page = 0) Pageable pageable);
    public ResponseEntity<Map<String, Object>> createSubject(@Valid SubjectDTO subjectData);
    public ResponseEntity<Map<String, Object>> updateSubject(String subjectCurrentName, SubjectDTO subjectData);
    //public ResponseEntity<Map<String, Object>>  deleteSubject(@Valid SubjectDTO subjectData);
}
