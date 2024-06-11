package com.iStudent.microservicos.town_country.model;

import com.iStudent.microservicos.town_country.model.base.BaseEntityWithIdLong;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(name = "country")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Country extends BaseEntityWithIdLong {

    @Column
    private String name;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private Set<Town> towns;

    @Column
    private int numberCitizens;
}
