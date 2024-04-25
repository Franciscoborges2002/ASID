package com.iStudent.microservicos.teachers.model;

import com.iStudent.microservicos.teachers.model.base.BaseEntityWithIdLong;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "subjects")
@Getter
@Setter
public class Subject extends BaseEntityWithIdLong {

    @Column(unique = true, nullable = false)
    private String name;

}
