package com.iStudent.microservicos.model.base;

import com.iStudent.microservicos.model.Town;
import com.iStudent.microservicos.model.enums.GenderEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@ToString
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

    @ManyToOne
    private Town town;
    //Ver o que fazer aqui

    @Column
    private String email;
}
