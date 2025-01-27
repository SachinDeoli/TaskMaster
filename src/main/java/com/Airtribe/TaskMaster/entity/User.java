package com.Airtribe.TaskMaster.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(unique = true, nullable = false)
    @Getter
    @Setter
    private String username;

    @Column(nullable = false)
    @Getter
    @Setter
    private String password;

    @Column(unique = true, nullable = false)
    @Getter
    @Setter
    private String email;

}