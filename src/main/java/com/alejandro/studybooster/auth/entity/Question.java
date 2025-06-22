package com.alejandro.studybooster.auth.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false)
    private String question;

    @Column(nullable = false, unique = false)
    private String answerExplanation;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionOption> options = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "correct_option_id", nullable = true)
    private QuestionOption correctOption;

    @ManyToMany(mappedBy = "questions")
    private Set<ContentModule> modules = new HashSet<>();




}
