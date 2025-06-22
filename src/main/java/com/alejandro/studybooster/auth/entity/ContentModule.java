package com.alejandro.studybooster.auth.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "content_modules")
public class ContentModule {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToMany
    @JoinTable (
    name = "modules_questions",
            joinColumns = @JoinColumn(name = "content_module_id"),
            inverseJoinColumns = @JoinColumn(name = "questions_id")

    )
    private Set<Question> questions = new HashSet<>();

    @ManyToMany
    @JoinTable (
            name = "modules_docs",
            joinColumns = @JoinColumn(name = "content_module_id"),
            inverseJoinColumns = @JoinColumn(name = "docs_id")

    )
    private Set<Doc> docs = new HashSet<>();
}
