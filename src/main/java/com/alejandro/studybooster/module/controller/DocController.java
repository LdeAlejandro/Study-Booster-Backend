package com.alejandro.studybooster.module.controller;

import com.alejandro.studybooster.module.controller.dto.Doc.CreateDocDTO;
import com.alejandro.studybooster.module.controller.dto.Doc.GetDocsDTO;
import com.alejandro.studybooster.module.controller.dto.Doc.UpdateDocDTO;
import com.alejandro.studybooster.module.service.DocService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/module/{moduleId}/doc")
public class DocController {

    private final DocService docService;

    public DocController(DocService docService) {

        this.docService =  docService;

    }

    // Get doc by id
    @GetMapping("/{docId}")
    public ResponseEntity<Map<String, Object>> getDocById(
            @PathVariable("docId") Long docId){

        return docService.getDocById(docId);

    }

    // create doc
    @PostMapping
    public ResponseEntity<Map<String, Object>> createDoc(
            @PathVariable ("moduleId") Long moduleId,
            @RequestBody CreateDocDTO createDocDTO){

        return docService.createDoc(moduleId, createDocDTO);

    }

    // update doc
    @PutMapping("/{docId}")
    public ResponseEntity<Map<String, Object>> updateDoc(
            @PathVariable("docId") Long docId,
            @RequestBody UpdateDocDTO updateDocDTO){

        return docService.updateDoc(docId, updateDocDTO);

    }

    // delete doc
    @DeleteMapping("/{docId}")
    public ResponseEntity<Map<String, Object>> deleteDoc(
            @PathVariable("docId") Long docId) {
        return docService.deleteDoc(docId);
    }

}
