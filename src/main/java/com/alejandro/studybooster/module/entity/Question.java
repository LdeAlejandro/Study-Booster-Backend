package com.alejandro.studybooster.module.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "questions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false)
    private String question;

    @Column(nullable = false, unique = false)
    private String answerExplanation;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // To avoid infinite recursion when serializing
    private List<QuestionOption> options = new ArrayList<>();

    @ManyToMany(mappedBy = "questions")
    private Set<ContentModule> modules = new HashSet<>();




}
