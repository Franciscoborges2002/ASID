package com.iStudent.microservicos.student.model;

import com.iStudent.microservicos.student.model.base.BaseEntityWithIdLong;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Country extends BaseEntityWithIdLong {

    @Column
    private String name;

    @OneToMany(mappedBy = "country")
    private Set<Town> towns;
}
