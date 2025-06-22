package com.alejandro.studybooster.auth.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false)
    private String SubjectName;

    // orphanRemoval: removes module from DB when it's no longer linked to this subject
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ContentModule> modules = new HashSet<>();

}
