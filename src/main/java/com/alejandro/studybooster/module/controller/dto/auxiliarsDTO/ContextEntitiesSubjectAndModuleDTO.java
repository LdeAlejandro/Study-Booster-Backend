package com.alejandro.studybooster.module.controller.dto.auxiliarsDTO;

import com.alejandro.studybooster.module.entity.ContentModule;
import com.alejandro.studybooster.module.entity.Subject;

public record ContextEntitiesSubjectAndModuleDTO(


        Subject subject,

        ContentModule module

) {
}
