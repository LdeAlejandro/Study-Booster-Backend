package com.alejandro.studybooster.module.controller.dto.Subject;

import com.alejandro.studybooster.module.controller.dto.ContentModule.GetContentModuleDTO;

import java.util.List;

public record GetSubjectDTO(
        Long id,
        String subjectName
){
}
