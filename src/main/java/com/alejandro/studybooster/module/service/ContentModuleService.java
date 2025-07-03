package com.alejandro.studybooster.module.service;

import com.alejandro.studybooster.module.controller.dto.ContentModule.GetContentModuleDTO;
import com.alejandro.studybooster.module.controller.dto.ContentModule.CreateContentModuleDTO;
import com.alejandro.studybooster.module.controller.dto.ContentModule.UpdateContentModuleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

public interface ContentModuleService {

    public Page<GetContentModuleDTO> getPageModules(Long subjectId, Pageable pageable);
    public ResponseEntity<Map<String, Object>> getModuleById(Long moduleId);
    public List<GetContentModuleDTO> getModulesWithDepth(Long subjectId, int depth);
    public ResponseEntity<Map<String, Object>> createModule(Long subjectId, CreateContentModuleDTO dto);
    public ResponseEntity<Map<String, Object>> updateModule (Long moduleId, UpdateContentModuleDTO dto);
    public ResponseEntity<Map<String, Object>> deleteModule(Long moduleId);


}
