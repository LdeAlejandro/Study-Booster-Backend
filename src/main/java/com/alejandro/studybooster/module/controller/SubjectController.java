package com.alejandro.studybooster.module.controller;

import com.alejandro.studybooster.module.controller.dto.Subject.GetSubjectDTO;
import com.alejandro.studybooster.module.controller.dto.Subject.SubjectDTO;
import com.alejandro.studybooster.module.entity.Subject;
import com.alejandro.studybooster.module.service.SubjectService;
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
    public Page<GetSubjectDTO> getSubjects(Pageable pageable){

        return subjectService.getSubjects(pageable);
    }

    @PostMapping("/subjects")
    public ResponseEntity createSubject(@RequestBody SubjectDTO subjectData) {

        return subjectService.createSubject(subjectData);

    }

    @PutMapping("/subjects/{subjectCurrentName}")
    @Transactional
    public ResponseEntity editSubject(@PathVariable String subjectCurrentName, @RequestBody SubjectDTO subjectData){
        return subjectService.updateSubject(subjectCurrentName, subjectData);
    }

    @DeleteMapping("/subjects/{subjectName}")
    @Transactional
    public ResponseEntity deleteSubject (@PathVariable String subjectName){
        return subjectService.deleteSubject(subjectName);
    }



    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
