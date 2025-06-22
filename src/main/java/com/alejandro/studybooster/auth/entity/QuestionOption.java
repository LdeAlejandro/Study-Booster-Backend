package com.alejandro.studybooster.auth.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "question_options")
public class QuestionOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable =false, unique = false)
    private String option;

    @ManyToOne
    @JoinColumn (name = "question_id", nullable = false)
    private Question question;

}
