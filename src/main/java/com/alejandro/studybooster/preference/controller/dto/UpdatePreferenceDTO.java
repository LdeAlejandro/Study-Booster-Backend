package com.alejandro.studybooster.preference.controller.dto;

import com.alejandro.studybooster.preference.entity.TimerInterval;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdatePreferenceDTO(
        @NotNull
        Long id,

        @NotEmpty
        String label,

        @NotNull
        TimerInterval interval,

        @NotNull
        Long lastNotifiedAt,

        Long subjectId,
        Long moduleId
) {}
