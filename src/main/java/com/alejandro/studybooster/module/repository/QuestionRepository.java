package com.alejandro.studybooster.module.repository;

import com.alejandro.studybooster.module.entity.QuestionOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<QuestionOption, Long> {

}
