package com.alejandro.studybooster.module.controller.dto.auxiliarsDTO;

import com.alejandro.studybooster.module.entity.ContentModule;
import com.alejandro.studybooster.module.entity.Question;
import com.alejandro.studybooster.module.entity.Subject;

public record ContextEntitiesSubjectModuleAndQuestionDTO(


        Subject subject,

        ContentModule module,

        Question question

) {
}
