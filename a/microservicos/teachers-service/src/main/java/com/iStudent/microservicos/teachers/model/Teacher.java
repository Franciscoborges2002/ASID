package com.iStudent.microservicos.teachers.model;

import com.iStudent.microservicos.teachers.model.base.BasePersonEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "teachers")
@Getter
@Setter
public class Teacher extends BasePersonEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder(super.toString());
        out.append(System.lineSeparator())
                .append("Subject: ").append(subject.getName()).append(System.lineSeparator());

        return out.toString().trim();

    }
}
