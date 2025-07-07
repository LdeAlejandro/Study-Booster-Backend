package com.alejandro.studybooster.preference.service;

import com.alejandro.studybooster.preference.controller.dto.CreatePreferenceDTO;
import com.alejandro.studybooster.preference.controller.dto.ResponsePreferenceDTO;
import com.alejandro.studybooster.preference.controller.dto.UpdatePreferenceDTO;
import com.alejandro.studybooster.preference.entity.Preference;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface PreferenceService {
    public List<ResponsePreferenceDTO> getAllPreferences();
    public ResponseEntity<Map<String, Object>> getRandomQuestionFromPreference(Long preferenceId);
    public ResponseEntity<Map<String, Object>> createPreference(CreatePreferenceDTO dto);
    public ResponseEntity<Map<String, Object>> updatePreference(UpdatePreferenceDTO dto);
    public ResponseEntity<Map<String, Object>> deletePreference(Long id);
}
