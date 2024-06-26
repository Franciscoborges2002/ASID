package com.iStudent.microservicos.town.model;

import com.iStudent.microservicos.town.model.base.BaseEntityWithIdLong;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "countries")
@Getter
@Setter
public class Country extends BaseEntityWithIdLong {

    @Column
    private String name;

    @OneToMany(mappedBy = "country")
    private Set<Town> towns;
}
