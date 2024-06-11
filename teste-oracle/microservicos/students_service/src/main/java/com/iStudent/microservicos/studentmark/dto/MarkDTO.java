package com.iStudent.microservicos.studentmark.dto;

import com.iStudent.microservicos.studentmark.model.enums.MarkEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarkDTO {
    @NotNull
    private MarkEnum mark;
}
