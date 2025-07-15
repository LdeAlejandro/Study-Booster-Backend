package com.alejandro.studybooster.preference.controller.dto;

public record ResponsePreferenceDTO(
        Long id,
        String moduleName,
        long interval,
        String stringInterval,
        Long subjectId,
        String subjectName,
        Long moduleId,
        Long lastNotifiedAt
) {
}
