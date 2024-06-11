package com.istudent.microservicos.parent.model;

import com.istudent.microservicos.parent.model.base.BaseEntityWithIdLong;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Town extends BaseEntityWithIdLong {

    public String name;
}
