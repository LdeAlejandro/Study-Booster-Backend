package com.alejandro.studybooster.preference.service.Impl;

import com.alejandro.studybooster.module.entity.ContentModule;
import com.alejandro.studybooster.module.entity.Question;
import com.alejandro.studybooster.module.repository.ContentModuleRepository;
import com.alejandro.studybooster.module.repository.SubjectRepository;
import com.alejandro.studybooster.preference.controller.dto.CreatePreferenceDTO;
import com.alejandro.studybooster.preference.controller.dto.ResponsePreferenceDTO;
import com.alejandro.studybooster.preference.controller.dto.UpdatePreferenceDTO;
import com.alejandro.studybooster.preference.entity.Preference;
import com.alejandro.studybooster.preference.repository.PreferenceRepository;
import com.alejandro.studybooster.preference.service.PreferenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PreferenceServiceImpl implements PreferenceService {

    private final PreferenceRepository preferenceRepository;
    private final SubjectRepository subjectRepository;
    private final ContentModuleRepository moduleRepository;

    public PreferenceServiceImpl(PreferenceRepository preferenceRepository, SubjectRepository subjectRepository, ContentModuleRepository moduleRepository) {
        this.preferenceRepository = preferenceRepository;
        this.subjectRepository = subjectRepository;
        this.moduleRepository = moduleRepository;
    }

    @Override
    public List<ResponsePreferenceDTO> getAllPreferences() {
        return preferenceRepository.findAll().stream().map(pref -> new ResponsePreferenceDTO(
                pref.getId(),
                pref.getLabel(),
                pref.getInterval().name(),
                pref.getSubject() != null ? pref.getSubject().getId() : null,
                pref.getSubject() != null ? pref.getSubject().getSubjectName() : null,
                pref.getModule() != null ? pref.getModule().getId() : null,
                pref.getModule() != null ? pref.getModule().getName() : null
        )).toList();
    }

    @Override
    public ResponseEntity<Map<String, Object>> createPreference(CreatePreferenceDTO dto) {
        Map<String, Object> response = new HashMap<>();

        Preference preference = new Preference();
        preference.setLabel(dto.label());
        preference.setInterval(dto.interval());

        if (dto.subjectId() != null) {
            subjectRepository.findById(dto.subjectId()).ifPresent(preference::setSubject);
        }

        if (dto.moduleId() != null) {
            moduleRepository.findById(dto.moduleId()).ifPresent(preference::setModule);
        }

        preferenceRepository.save(preference);
        response.put("message", "Preference created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> updatePreference(UpdatePreferenceDTO dto) {
        Map<String, Object> response = new HashMap<>();

        Optional<Preference> optionalPreference = preferenceRepository.findById(dto.id());
        if (optionalPreference.isEmpty()) {
            response.put("message", "Preference not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Preference preference = optionalPreference.get();
        preference.setLabel(dto.label());
        preference.setInterval(dto.interval());

        if (dto.subjectId() != null) {
            subjectRepository.findById(dto.subjectId()).ifPresent(preference::setSubject);
        } else {
            preference.setSubject(null);
        }

        if (dto.moduleId() != null) {
            moduleRepository.findById(dto.moduleId()).ifPresent(preference::setModule);
        } else {
            preference.setModule(null);
        }

        preferenceRepository.save(preference);
        response.put("message", "Preference updated successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> deletePreference(Long id) {
        Map<String, Object> response = new HashMap<>();
        preferenceRepository.deleteById(id);
        response.put("message", "Preference deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getRandomQuestionFromPreference(Long preferenceId) {
        Optional<Preference> prefOpt = preferenceRepository.findById(preferenceId);
        Map<String, Object> response = new HashMap<>();

        if (prefOpt.isEmpty()) {
            response.put("message", "Preference not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Preference pref = prefOpt.get();
        List<Question> allQuestions = new ArrayList<>();

        if (pref.getModule() != null) {
            collectQuestionsRecursive(pref.getModule(), allQuestions);
        } else if (pref.getSubject() != null) {
            pref.getSubject().getModules().forEach(m -> collectQuestionsRecursive(m, allQuestions));
        }

        if (allQuestions.isEmpty()) {
            response.put("message", "No questions found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Question randomQuestion = allQuestions.get(new Random().nextInt(allQuestions.size()));

        if (pref.getModule() != null) {
            response.put("source", "module");
            response.put("source_id", pref.getModule().getId());
            response.put("source_name", pref.getModule().getName());
        } else if (pref.getSubject() != null) {
            response.put("source", "subject");
            response.put("source_id", pref.getSubject().getId());
            response.put("source_name", pref.getSubject().getSubjectName());
        }
        response.put("question_id", randomQuestion.getId());
        response.put("question", randomQuestion.getQuestion());
        response.put("question_answer_explanation", randomQuestion.getAnswerExplanation());
        response.put("options", randomQuestion.getOptions().stream().map(opt -> Map.of(
                "id", opt.getId(),
                "option", opt.getOption(),
                "correct", opt.isCorrectOption()
        )));

        return ResponseEntity.ok(response);
    }

    private void collectQuestionsRecursive(ContentModule module, List<Question> collector) {
        collector.addAll(module.getQuestions());
        for (ContentModule child : module.getChildren()) {
            collectQuestionsRecursive(child, collector);
        }
    }
}
