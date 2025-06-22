package com.alejandro.studybooster.auth.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "groups")
public class Group {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "groups") // Indica ao JPA que este é o lado inverso do relacionamento bidirecional; a tabela de junção é definida na entidade User
    private Set<User> users = new HashSet<>();
}
