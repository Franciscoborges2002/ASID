package com.iStudent.microservicos.teachers.model;

import com.iStudent.microservicos.teachers.model.base.BaseEntityWithIdLong;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Country extends BaseEntityWithIdLong {

    @Column
    private String name;

    @OneToMany(mappedBy = "country")
    private Set<Town> towns;

    public Country(){
        this.towns = new HashSet<>();
    }

}
