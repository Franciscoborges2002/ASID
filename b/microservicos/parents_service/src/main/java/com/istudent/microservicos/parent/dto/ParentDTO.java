package com.istudent.microservicos.parent.dto;

import com.istudent.microservicos.parent.dto.base.PersonEntityDTO;
import com.istudent.microservicos.parent.model.validation.PhoneNumber;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
public class ParentDTO extends PersonEntityDTO {

    @NotBlank
    @PhoneNumber
    private String phoneNumber;
}
