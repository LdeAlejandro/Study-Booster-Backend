package com.alejandro.studybooster.preference.controller.dto;


import com.alejandro.studybooster.preference.entity.TimerInterval;
import jakarta.validation.constraints.NotNull;

public record CreatePreferenceDTO(

        String moduleName,

        @NotNull
        TimerInterval interval,

        Long subjectId,
        Long moduleId,

        @NotNull
        Long lastNotifiedAt
) {}
