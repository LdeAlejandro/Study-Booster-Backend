package com.alejandro.studybooster.module.repository;

import com.alejandro.studybooster.module.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionOptionRepository extends JpaRepository<Question, Long>{

}
