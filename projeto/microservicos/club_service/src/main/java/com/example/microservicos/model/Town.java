package com.example.microservicos.model;

import com.example.microservicos.model.base.BaseEntityWithIdLong;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Town extends BaseEntityWithIdLong {

    @OneToOne
    @MapsId
    @JoinColumn(name = "clubs_id")
    private Club club;
}
