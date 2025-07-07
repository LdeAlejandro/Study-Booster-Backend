package com.alejandro.studybooster.preference.controller.dto;

import com.alejandro.studybooster.preference.entity.TimerInterval;
import jakarta.validation.constraints.NotNull;

public record UpdatePreferenceDTO(
        @NotNull
        Long id,

        String label,

        @NotNull
        TimerInterval interval,

        Long subjectId,
        Long moduleId
) {}
