package com.alejandro.studybooster.preference.entity;

import com.alejandro.studybooster.module.entity.ContentModule;

import com.alejandro.studybooster.module.entity.Subject;
import jakarta.persistence.*;

import lombok.*;



@Entity
@Table(name = "preferences")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Preference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private ContentModule module;

    @Enumerated(EnumType.STRING)
    private TimerInterval interval; // ENUM: FIFTEEN_MIN, THIRTY_MIN, SIXTY_MIN
}

