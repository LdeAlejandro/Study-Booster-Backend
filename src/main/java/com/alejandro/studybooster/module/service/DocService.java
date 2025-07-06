package com.alejandro.studybooster.module.service;

import com.alejandro.studybooster.module.controller.dto.Doc.CreateDocDTO;
import com.alejandro.studybooster.module.controller.dto.Doc.GetDocsDTO;
import com.alejandro.studybooster.module.controller.dto.Doc.UpdateDocDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;


public interface DocService {

    //create doc
    public ResponseEntity<Map<String, Object>> getDocById(Long docId);
    public ResponseEntity<Map<String, Object>> createDoc(Long moduleId, CreateDocDTO createDocDTO);
    public ResponseEntity<Map<String, Object>> updateDoc(Long docId, UpdateDocDTO updateDocDTO);
    public ResponseEntity<Map<String, Object>> deleteDoc(Long docId);
}
