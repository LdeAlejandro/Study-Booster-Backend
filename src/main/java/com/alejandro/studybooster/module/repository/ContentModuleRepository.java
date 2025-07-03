package com.alejandro.studybooster.module.repository;

import com.alejandro.studybooster.module.entity.ContentModule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContentModuleRepository extends JpaRepository<ContentModule, Long> {
        Optional<ContentModule> findBySubjectIdAndName(Long subjectId, String name);
        Page<ContentModule> findBySubjectId(Long subjectId, Pageable pageable);
        Optional<ContentModule> findById(Long id);
}
