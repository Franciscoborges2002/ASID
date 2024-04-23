package com.iStudent.microservicos.model;

import com.iStudent.microservicos.model.base.BaseEntityWithIdLong;
import jakarta.persistence.*;

@Entity
@Table(name = "towns")
public class Town extends BaseEntityWithIdLong {
    @Column
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "country_id")
    private Country country;
}
