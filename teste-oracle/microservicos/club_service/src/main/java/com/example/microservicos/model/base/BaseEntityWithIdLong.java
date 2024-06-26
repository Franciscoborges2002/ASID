package com.example.microservicos.model.base;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class BaseEntityWithIdLong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
