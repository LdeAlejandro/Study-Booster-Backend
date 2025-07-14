package com.alejandro.studybooster.module.repository;

import com.alejandro.studybooster.module.controller.dto.ContentModule.GetContentModuleDTO;
import com.alejandro.studybooster.module.entity.ContentModule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContentModuleRepository extends JpaRepository<ContentModule, Long> {
        Optional<ContentModule> findBySubjectIdAndName(Long subjectId, String name);
        Page<ContentModule> findBySubjectId(Long subjectId, Pageable pageable);
        Optional<ContentModule> findById(Long id);

        List<ContentModule> findBySubjectIdAndParentIsNull(Long subjectId);

        List<ContentModule> findByNameContainingIgnoreCase(String name);

        Page<ContentModule> findBySubjectIdAndParentId(Long subjectId, Long parentId, Pageable pageable);

        Page<ContentModule> findBySubjectIdAndParentIsNull(Long subjectId, Pageable pageable);
}
