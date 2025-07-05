package com.alejandro.studybooster.module.repository;

import com.alejandro.studybooster.module.entity.Question;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Optional<Question> findById(Long id);
    Page<Question> findAllByModules_Id(Long moduleId, Pageable pageable);
}
