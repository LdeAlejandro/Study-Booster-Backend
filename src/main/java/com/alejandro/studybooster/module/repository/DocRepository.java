package com.alejandro.studybooster.module.repository;

import com.alejandro.studybooster.module.controller.dto.Doc.GetDocsDTO;
import com.alejandro.studybooster.module.entity.Doc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface DocRepository extends JpaRepository<Doc, Long> {

    // find docs by module id and return a set of GetDocsDTO objects with id and title so it doesnt include LOB content
    @Query("SELECT new com.alejandro.studybooster.module.controller.dto.Doc.GetDocsDTO(d.id, d.title) " +
            "FROM ContentModule m JOIN m.docs d WHERE m.id = :moduleId")
    Set<GetDocsDTO> findDocsByModuleId(@Param("moduleId") Long moduleId);
}
