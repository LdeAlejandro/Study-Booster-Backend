package com.alejandro.studybooster.auth.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "Docs")
public class Doc {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false)
    private String title;


    @Column(nullable = false, unique = false)
    private String content;

    @ManyToMany (mappedBy = "docs")
    private Set<ContentModule> modules = new HashSet<>();
}
