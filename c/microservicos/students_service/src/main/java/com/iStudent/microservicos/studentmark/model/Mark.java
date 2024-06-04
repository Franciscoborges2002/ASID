package com.iStudent.microservicos.studentmark.model;

import com.iStudent.microservicos.studentmark.model.base.BaseEntityWithIdLong;
import com.iStudent.microservicos.studentmark.model.enums.MarkEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mark extends BaseEntityWithIdLong {
    @Enumerated(EnumType.STRING)
    private MarkEnum mark;
}
