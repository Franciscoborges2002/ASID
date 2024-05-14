package com.iStudent.microservicos.model;

import com.iStudent.microservicos.model.base.BaseEntityWithIdLong;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Town extends BaseEntityWithIdLong {
    @Column
    private String name;
}
