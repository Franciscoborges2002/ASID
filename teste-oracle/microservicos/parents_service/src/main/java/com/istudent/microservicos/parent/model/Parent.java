package com.istudent.microservicos.parent.model;

import com.istudent.microservicos.parent.model.base.BasePersonEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "parent")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Parent extends BasePersonEntity {

    @Column
    private String phoneNumber;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    Set<Student> students;
}
