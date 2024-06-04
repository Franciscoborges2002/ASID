package com.iStudent.microservicos.town_country.model;

import com.iStudent.microservicos.town_country.model.base.BaseEntityWithIdLong;
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
    @JoinColumn(name = "country_id")//, fetch = FetchType.LAZY
    private Country country;

    @Column
    private int numberCitizens;

    @OneToOne(mappedBy = "town", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Club club;
}
