package com.iStudent.microservicos.sagaorchestrator.dto;

import com.iStudent.microservicos.sagaorchestrator.model.Club;
import com.iStudent.microservicos.sagaorchestrator.model.enums.OperationStatusEnum;
import com.iStudent.microservicos.sagaorchestrator.model.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClubStudentDTO {

    //Have the status of the operation
    private OperationStatusEnum operationStatus;

    //Have the reason why it failed or the error
    private String reason;

    //Associated club that was added
    private Club club;

    //Associated Student that was added
    private Student student;

}
