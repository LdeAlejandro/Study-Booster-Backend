package com.alejandro.studybooster.module.controller;

import com.alejandro.studybooster.module.controller.dto.SubjectDTO;
import com.alejandro.studybooster.module.entity.Subject;
import com.alejandro.studybooster.module.service.SubjectService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController (SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping("/create-subject")
    public ResponseEntity createSubject(@RequestBody SubjectDTO subjectData) {

        return subjectService.createSubject(subjectData);

    }

    @GetMapping("/subjects")
    public Page<Subject> getSubjects(Pageable pageable){

        return subjectService.getSubjects(pageable);
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
