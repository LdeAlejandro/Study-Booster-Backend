package com.alejandro.studybooster.preference.controller.dto;

public record ResponsePreferenceDTO(
        Long id,
        String label,
        String interval,
        Long subjectId,
        String subjectName,
        Long moduleId,
        String moduleName
) {
}
