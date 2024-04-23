package com.iStudent.microservicos.model;

import com.iStudent.microservicos.model.base.BaseEntityWithIdLong;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "countries")
public class Country extends BaseEntityWithIdLong {
    @Column
    private String name;

    @OneToMany(mappedBy = "country")
    private Set<Town> towns;
}
