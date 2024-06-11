package com.iStudent.microservicos.studentmark.model.base;

import com.iStudent.microservicos.studentmark.model.enums.GenderEnum;
import jakarta.persistence.*;
import lombok.*;

@MappedSuperclass
@Getter
@Setter
public class BasePersonEntity extends BaseEntityWithIdLong {
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name", nullable = false)
    private String middleName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String EGN;

    @Column(nullable = false)
    private int age;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @Column
    private String town;

    @Column
    private String email;
}
