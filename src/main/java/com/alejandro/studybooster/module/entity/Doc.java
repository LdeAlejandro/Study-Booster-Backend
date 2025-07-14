package com.alejandro.studybooster.module.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table (name = "Docs")
public class Doc {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @ManyToMany(mappedBy = "docs", cascade = CascadeType.ALL) // inverse side of the relationship with ContentModule now docs can be deleted
    private Set<ContentModule> modules = new HashSet<>();
}
