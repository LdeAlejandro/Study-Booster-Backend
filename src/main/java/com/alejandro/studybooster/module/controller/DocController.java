package com.alejandro.studybooster.module.controller;

import com.alejandro.studybooster.module.service.DocService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/module/{moduleId}/docs/{docId}")
public class DocController {

    private final DocService docService;

    public DocController(DocService docService) {

        this.docService =  docService;

    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createDoc(@RequestBody createDocDTO createDocDTO){
        return docService.createDoc(createDocDTO);
    }

}
