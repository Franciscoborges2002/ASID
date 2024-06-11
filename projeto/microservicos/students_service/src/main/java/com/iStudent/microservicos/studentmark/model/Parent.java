package com.iStudent.microservicos.studentmark.model;

import com.iStudent.microservicos.studentmark.model.base.BaseEntityWithIdLong;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Parent extends BaseEntityWithIdLong {

    private String firstName;

    private String lastName;
}
