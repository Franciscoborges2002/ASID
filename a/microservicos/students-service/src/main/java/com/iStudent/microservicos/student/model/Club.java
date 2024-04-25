package com.iStudent.microservicos.student.model;

import com.iStudent.microservicos.student.model.base.BaseEntityWithIdLong;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clubs")
@Getter
@Setter
public class Club extends BaseEntityWithIdLong {

    @Column(unique = true)
    private String name;

    @Column(length = 500, columnDefinition = "text")
    private String description;

    @ManyToMany(mappedBy = "clubs", fetch = FetchType.LAZY)
    private Set<Student> students;

    public Club() {
        this.students = new HashSet<>();
    }
}
