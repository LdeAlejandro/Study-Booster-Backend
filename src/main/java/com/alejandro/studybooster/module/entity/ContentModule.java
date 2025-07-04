package com.alejandro.studybooster.module.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
// unique constraints para que cada modulo dentro de um subject seja unico pelo string name
@Table(name = "content_modules",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"subject_id", "name"})
        })
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContentModule {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn (name = "subject_id", nullable = false)
    @JsonBackReference(value = "subject-module")
    private Subject subject;

    //To allow the module to be a submodule (child module)
    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonBackReference(value = "parent-child")  // To avoid infinite recursion when serializing (this object will be ignored in the json)
    private ContentModule parent;

    //To allow the module to have submodules (Parent module)
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "parent-child")// To avoid infinite recursion when serializing (this object will be included in the json)
    private Set<ContentModule> children = new HashSet<>();

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
