package com.iStudent.microservicos.student.dto;

import com.iStudent.microservicos.student.model.enums.MarkEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarkDTO {
    @NotNull
    private MarkEnum mark;

}
