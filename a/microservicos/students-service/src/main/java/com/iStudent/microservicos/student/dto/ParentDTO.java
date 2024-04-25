package com.iStudent.microservicos.student.dto;


import com.iStudent.microservicos.student.dto.base.PersonEntityDTO;
import com.iStudent.microservicos.student.model.validation.PhoneNumber;
import jakarta.validation.constraints.NotBlank;

public class ParentDTO extends PersonEntityDTO {

    @NotBlank
    @PhoneNumber
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
