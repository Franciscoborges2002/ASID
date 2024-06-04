package com.iStudent.microservicos.town_country.model;

import com.iStudent.microservicos.town_country.model.base.BaseEntityWithIdLong;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Club extends BaseEntityWithIdLong {

    private String name;


    @OneToOne
    @MapsId
    @JoinColumn(name = "towns_id")
    private Town town;
}
