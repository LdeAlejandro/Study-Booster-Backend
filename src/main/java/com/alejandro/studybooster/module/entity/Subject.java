package com.alejandro.studybooster.module.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "subjects")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String subjectName;

    // orphanRemoval: removes module from DB when it's no longer linked to this subject
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "subject-module") // To avoid infinite recursion when serializing
    private Set<ContentModule> modules = new HashSet<>();

}
