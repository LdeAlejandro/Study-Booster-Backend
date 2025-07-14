package com.alejandro.studybooster.module.controller;

import com.alejandro.studybooster.module.controller.dto.ContentModule.CreateContentModuleDTO;
import com.alejandro.studybooster.module.controller.dto.ContentModule.GetContentModuleDTO;
import com.alejandro.studybooster.module.controller.dto.ContentModule.UpdateContentModuleDTO;
import com.alejandro.studybooster.module.service.ContentModuleService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/subject/{subjectId}/module")
public class ContentModuleController {

    private final ContentModuleService contentModuleService;

    public ContentModuleController(ContentModuleService contentModuleService) {
        this.contentModuleService = contentModuleService;
    }

    //Get all modules pageable
    @GetMapping("/get-page-modules")
    public Page<GetContentModuleDTO> getPageModules(@PathVariable("subjectId") Long subjectId,
                                                    @PageableDefault(size = 12, page = 0) Pageable pageable ) {
        return contentModuleService.getPageModules(subjectId, pageable);
    }

    //Get module by ID
    @GetMapping("/{moduleId}")
    public ResponseEntity<Map<String, Object>> getModuleById(@PathVariable("moduleId") Long moduleId) {
        return contentModuleService.getModuleById(moduleId);
    }

    //Get modules by depth
    @GetMapping("/parent")
    public Page<GetContentModuleDTO> getModulesBySubjecyAndParent(
            @PathVariable("subjectId") Long subjectId,
            @RequestParam(required = false) Long parentId,
            @PageableDefault(size = 12, page = 0) Pageable pageable) {
        return contentModuleService.getModulesByParent(subjectId, parentId, pageable);
    }

    //Create module
    @PostMapping
    public ResponseEntity<Map<String, Object>> createModule(
            @PathVariable("subjectId") Long subjectId,
            @Valid @RequestBody CreateContentModuleDTO createContentModuleDTO) {

        return contentModuleService.createModule(subjectId, createContentModuleDTO);

    }

    //Update module
    @PatchMapping("/{moduleId}")
    public ResponseEntity<Map<String, Object>> updateModule(
            @PathVariable("moduleId") Long moduleId,
            @Valid @RequestBody UpdateContentModuleDTO updateContentModuleDTO
            ){
        return contentModuleService.updateModule(moduleId, updateContentModuleDTO);

    }

    //Delete module
    @DeleteMapping("/{moduleId}")
    public ResponseEntity<Map<String, Object>> deleteModule(@PathVariable("moduleId") Long moduleId) {

        return contentModuleService.deleteModule(moduleId);

    }

}
