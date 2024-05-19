package com.example.microservicos.model;

import jakarta.persistence.*;
import org.springframework.context.annotation.Primary;

import java.util.Set;

@Entity
public class Student {

    @Id
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "students_clubs",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "club_id", referencedColumnName = "id")
    )
    private Set<Club> clubs;
}
