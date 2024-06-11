package com.iStudent.microservicos.student.model;

import com.iStudent.microservicos.student.model.base.BaseEntityWithIdLong;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Town extends BaseEntityWithIdLong {

    @Column
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "country_id")
    private Country country;
}
