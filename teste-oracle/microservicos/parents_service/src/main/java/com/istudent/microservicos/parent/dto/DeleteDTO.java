package com.istudent.microservicos.parent.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DeleteDTO {
    private Long id;

    private String message;
}
