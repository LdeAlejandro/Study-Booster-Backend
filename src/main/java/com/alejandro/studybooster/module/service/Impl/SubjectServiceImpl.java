package com.alejandro.studybooster.module.service.Impl;

import com.alejandro.studybooster.module.controller.dto.SubjectDTO;
import com.alejandro.studybooster.module.entity.Subject;
import com.alejandro.studybooster.module.repository.SubjectRepository;
import com.alejandro.studybooster.module.service.SubjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {


    private final SubjectRepository subjectRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository) {

        this.subjectRepository = subjectRepository;
    }

    //get subjects
    @Override
    public Page<Subject> getSubjects(@PageableDefault(size = 10, page = 0) Pageable pageable){

        // limit page elements to 12
        //create a sage pagination
        Pageable safePageable = PageRequest.of(
                pageable.getPageNumber(), // check number of page in the request
                Math.min(pageable.getPageSize(), 12), // limit to 12
                Sort.by(Sort.Direction.DESC, "id")
        );


        return subjectRepository.findAll(safePageable);
    }

    //create subject
    @Override
    public ResponseEntity<Map<String, Object>> createSubject(SubjectDTO subjectData){

        Optional<Subject> existingSubject = subjectRepository.findBySubjectName(subjectData.subjectName());

        // check if subject name is empty
        if (subjectData.subjectName() == null || subjectData.subjectName().equals("")) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Subject Name can´t be empty, please change the subject");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // check if subject name already exists, (CHANGE THIS LATER WHEN IMPLEMENTING GROUPS)
        if (existingSubject.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Subject Name already exists, please change the subject name");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        //save new subject
        Subject newSubject = new Subject();
        newSubject.setSubjectName(subjectData.subjectName());
        subjectRepository.save(newSubject);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Subject created successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }





}
