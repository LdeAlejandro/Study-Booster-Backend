package com.alejandro.studybooster.module.controller;

import com.alejandro.studybooster.module.service.ContentModuleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/modules")
public class AnyContentModuleController {

    private final ContentModuleService contentModuleService;

    public AnyContentModuleController(ContentModuleService contentModuleService) {
        this.contentModuleService = contentModuleService;
    }


    @GetMapping("/summary")
    public List<Map<String, Object>> getAllModulesSummary() {
        return contentModuleService.getAllModuleSummaries();
    }

    @GetMapping("/search")
    public List<Map<String, Object>> searchModulesByName(@RequestParam String query) {
        return contentModuleService.searchModulesByName(query);
    }
}
