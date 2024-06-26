package com.istudent.microservicos.parent.model;

import com.istudent.microservicos.parent.model.base.BaseEntityWithIdLong;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Student extends BaseEntityWithIdLong {

    @ManyToOne
    private Parent parent;
}
