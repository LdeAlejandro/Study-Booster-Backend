package com.alejandro.studybooster.module.controller;

import com.alejandro.studybooster.module.controller.dto.SubjectDTO;
import com.alejandro.studybooster.module.service.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController (SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping("/create-subject")
    public ResponseEntity createSubject(SubjectDTO subjectData) {

        return subjectService.createSubject(subjectData);

    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
