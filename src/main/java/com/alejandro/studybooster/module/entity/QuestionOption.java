package com.alejandro.studybooster.module.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "question_options")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuestionOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable =false, unique = false)
    private String option;

    @Column(nullable = false)
    private boolean correctOption;

    @ManyToOne
    @JsonBackReference // To avoid infinite recursion when serializing
    @JoinColumn (name = "question_id", nullable = false)
    private Question question;

}
