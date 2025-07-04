package com.alejandro.studybooster.module.repository;

import com.alejandro.studybooster.module.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Optional <Subject> findById(Long id);
    Optional<Subject> findBySubjectName(String subjectName);
}
