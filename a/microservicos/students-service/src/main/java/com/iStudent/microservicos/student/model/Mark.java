package com.iStudent.microservicos.student.model;

import com.iStudent.microservicos.student.model.enums.MarkEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "marks")
@Getter
@Setter
public class Mark {

    @Enumerated(EnumType.STRING)
    private MarkEnum mark;
}
