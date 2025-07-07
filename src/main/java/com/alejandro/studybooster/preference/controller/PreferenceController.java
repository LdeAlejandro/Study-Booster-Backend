package com.alejandro.studybooster.preference.controller;

import com.alejandro.studybooster.preference.controller.dto.CreatePreferenceDTO;
import com.alejandro.studybooster.preference.controller.dto.ResponsePreferenceDTO;
import com.alejandro.studybooster.preference.controller.dto.UpdatePreferenceDTO;
import com.alejandro.studybooster.preference.entity.Preference;
import com.alejandro.studybooster.preference.service.PreferenceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/preferences")
public class PreferenceController {

    private final PreferenceService preferenceService;

    public PreferenceController(PreferenceService preferenceService) {
        this.preferenceService = preferenceService;
    }

    @GetMapping
    public List<ResponsePreferenceDTO> getAllPreferences() {
        return preferenceService.getAllPreferences();
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createPreference(@Valid @RequestBody CreatePreferenceDTO dto) {
        return preferenceService.createPreference(dto);
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> updatePreference(@Valid @RequestBody UpdatePreferenceDTO dto) {
        return preferenceService.updatePreference(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deletePreference(@PathVariable Long id) {
        return preferenceService.deletePreference(id);
    }

    @GetMapping("/{id}/random-question")
    public ResponseEntity<Map<String, Object>> getRandomQuestion(@PathVariable Long id) {
        return preferenceService.getRandomQuestionFromPreference(id);
    }
}
