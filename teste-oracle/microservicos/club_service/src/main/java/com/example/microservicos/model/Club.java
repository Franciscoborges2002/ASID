package com.example.microservicos.model;

import com.example.microservicos.model.base.BaseEntityWithIdLong;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clubs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Club extends BaseEntityWithIdLong {


    @Column(unique = true)
    private String name;

    @Column(length = 500, columnDefinition = "text")
    private String description;

    @ManyToMany(mappedBy = "clubs", fetch = FetchType.LAZY)
    private Set<Student> students;

    @OneToOne(mappedBy = "club", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Town town;
}
