package com.alejandro.studybooster.module.repository;

import com.alejandro.studybooster.module.entity.Doc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocRepository extends JpaRepository<Doc, Long> {

}
