package com.alejandro.studybooster.module.repository;

import com.alejandro.studybooster.module.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
