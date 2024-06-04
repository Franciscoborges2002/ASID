package com.iStudent.microservicos.studentmark.model;

import com.iStudent.microservicos.studentmark.model.base.BaseEntityWithIdLong;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Town {

    private Long id;

    private String name;

    private Country country;
}
