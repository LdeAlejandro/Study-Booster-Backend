package com.alejandro.studybooster.module.controller;

import com.alejandro.studybooster.module.controller.dto.SubjectDTO;
import com.alejandro.studybooster.module.entity.Subject;
import com.alejandro.studybooster.module.service.SubjectService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController (SubjectService subjectService) {

        this.subjectService = subjectService;
    }

    @GetMapping("/subjects")
    public Page<Subject> getSubjects(Pageable pageable){

        return subjectService.getSubjects(pageable);
    }

    @PostMapping("/create-subject")
    public ResponseEntity createSubject(@RequestBody SubjectDTO subjectData) {

        return subjectService.createSubject(subjectData);

    }

    @PutMapping("/edit-subject/{subjectCurrentName}")
    @Transactional
    public ResponseEntity editSubject(@PathVariable String subjectCurrentName, @RequestBody SubjectDTO subjectData){
        return subjectService.updateSubject(subjectCurrentName, subjectData);
    }

    @DeleteMapping("/delete-subject")
    @Transactional
    public ResponseEntity deleteSubject (@RequestBody SubjectDTO subjectData){
        return subjectService.deleteSubject(subjectData);
    }



    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
