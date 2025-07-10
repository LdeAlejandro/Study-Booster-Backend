package com.alejandro.studybooster.preference.controller.dto;

public record ResponsePreferenceDTO(
        Long id,
        String label,
        long interval,
        String stringInterval,
        Long subjectId,
        String subjectName,
        Long moduleId,
        String moduleName,
        Long lastNotifiedAt
) {
}
