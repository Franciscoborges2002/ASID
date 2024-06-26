package com.iStudent.microservicos.town.model;

import com.iStudent.microservicos.town.model.base.BaseEntityWithIdLong;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "towns")
@Getter
@Setter
public class Town extends BaseEntityWithIdLong {

    @Column
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "country_id")
    private Country country;
}
