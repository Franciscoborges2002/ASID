package com.istudent.microservicos.parent.model.base;

import com.istudent.microservicos.parent.model.Town;
import com.istudent.microservicos.parent.model.enums.GenderEnum;
import jakarta.persistence.*;
import lombok.*;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @Column
    private String town;

    @Column
    private String email;
}
