package com.iStudent.microservicos.model;

import com.iStudent.microservicos.model.base.BasePersonEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends BasePersonEntity {
    @Column(name = "job_title", nullable = false)
    private String jobTitle;

    @Column(name = "work_hours", nullable = false)
    private int workHours;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
}
