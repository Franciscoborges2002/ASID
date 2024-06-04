package com.iStudent.microservicos.sagaorchestrator.dto;

import com.iStudent.microservicos.sagaorchestrator.dto.base.PersonEntityDTO;
import com.iStudent.microservicos.sagaorchestrator.model.validation.PhoneNumber;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ParentDTO extends PersonEntityDTO {

    @NotBlank
    @PhoneNumber
    private String phoneNumber;
}
