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


        // response object
        Map<String, Object> response = new HashMap<>();

        // check if subject name is empty
        if (subjectData.subjectName() == null || subjectData.subjectName().equals("")) {
            response.put("message", "Subject Name can´t be empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        //Check if the subject name already exists in DB
        Optional<Subject> existingSubject = subjectRepository.findBySubjectName(subjectData.subjectName());


        // check if subject name already exists, (CHANGE THIS LATER WHEN IMPLEMENTING GROUPS)
        if (existingSubject.isPresent()) {
            response.put("message", "Subject Name already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }



        //save new subject
        Subject newSubject = new Subject();
        newSubject.setSubjectName(subjectData.subjectName());
        subjectRepository.save(newSubject);

        response.put("message", "Subject created successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @Override
    public ResponseEntity<Map<String,Object>> updateSubject(String subjectCurrentName, SubjectDTO subjectData){

        // response object
        Map<String, Object> response = new HashMap<>();

        // check if the current Subject name is empty
        if (subjectCurrentName == null || subjectCurrentName.isBlank()) {

            response.put("message", "The Current Subject Name can´t be empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

            // check if the new Subject  name is empty
            if (subjectData.subjectName() == null || subjectData.subjectName().isBlank()) {

            response.put("message", "Subject Name can´t be empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        //Check if the subject name already exists in DB
        Optional<Subject> existingSubject = subjectRepository.findBySubjectName(subjectCurrentName);

        //save updated Subject
        Subject updatedSubject = existingSubject.get();
        updatedSubject.setSubjectName(subjectData.subjectName());
        subjectRepository.save(updatedSubject);

        response.put("message", "Subject Name updated successfully");

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteSubject(String subjectName){

        Map<String, Object> response = new HashMap<>();

        // check if subject name is empty
        if (subjectName == null || subjectName.equals("")) {
            response.put("message", "Subject Name can´t be empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        //Check if the subject name already exists in DB
        Optional<Subject> existingSubject = subjectRepository.findBySubjectName(subjectName);

        // check if subject does not exist in DB
        if (existingSubject.isEmpty()) {
            response.put("message", "Subject does not exist in DB");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // check if subject name already exists, (CHANGE THIS LATER WHEN IMPLEMENTING GROUPS)
        else{
            subjectRepository.delete(existingSubject.get());
            response.put("message", "Subject Name found and deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

    }


}
