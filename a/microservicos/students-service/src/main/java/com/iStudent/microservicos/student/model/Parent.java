package com.iStudent.microservicos.student.model;

import com.iStudent.microservicos.student.model.base.BasePersonEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class Parent extends BasePersonEntity {

    @Column
    private String phoneNumber;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    Set<Student> students;
}
