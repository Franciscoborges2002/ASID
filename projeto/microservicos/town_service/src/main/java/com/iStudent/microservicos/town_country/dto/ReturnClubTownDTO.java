package com.iStudent.microservicos.town_country.dto;

import com.iStudent.microservicos.town_country.model.enums.OperationStatusEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReturnClubTownDTO {

    //Have the status of the operation
    private OperationStatusEnum operationStatus;

    //Have the reason why it failed or the error
    private String reason;

    private ClubDTO clubDTO;

    private TownDTO townDTO;
}
