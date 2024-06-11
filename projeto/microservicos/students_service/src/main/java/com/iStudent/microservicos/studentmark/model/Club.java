package com.iStudent.microservicos.studentmark.model;

import com.iStudent.microservicos.studentmark.model.base.BaseEntityWithIdLong;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Club extends BaseEntityWithIdLong {

    //private String name;

    @ManyToMany(mappedBy = "clubs", fetch = FetchType.LAZY)
    private Set<Student> students;
}
