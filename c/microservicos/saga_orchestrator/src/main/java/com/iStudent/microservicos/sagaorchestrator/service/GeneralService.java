package com.iStudent.microservicos.sagaorchestrator.service;

import com.iStudent.microservicos.sagaorchestrator.dto.*;
import com.iStudent.microservicos.sagaorchestrator.extern.ClubCallerService;
import com.iStudent.microservicos.sagaorchestrator.extern.ParentCallerService;
import com.iStudent.microservicos.sagaorchestrator.extern.StudentCallerService;
import com.iStudent.microservicos.sagaorchestrator.extern.TownCallerService;
import com.iStudent.microservicos.sagaorchestrator.model.enums.OperationStatusEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@AllArgsConstructor
public class GeneralService {

    private final StudentCallerService studentCallerService;
    private final ParentCallerService parentCallerService;
    private final ClubCallerService clubCallerService;
    private final TownCallerService townCallerService;

    //Function to create a student
    public ReturnCreateStudentDTO createStudentParent(RequestCreateStudentDTO requestCreateStudentDTO){
        //Object to return
        ReturnCreateStudentDTO returnCreateStudentDTO = new ReturnCreateStudentDTO();

        //Know what was made or not
        boolean studentCreated = false;
        boolean parentCreated = false;

        //Create
        ParentDTO parentDTO = new ParentDTO();
        StudentDTO studentDTOtoSend = new StudentDTO();

        try{
            //Send request tot parent service
            parentDTO = parentCallerService.createParent(requestCreateStudentDTO.getParent());

            if(parentDTO.getFirstName() != null){
                parentCreated = true;
            }

            //Create an instance to give to student dto
            ParentDTOtoStudent parentDTOtoStudent = new ParentDTOtoStudent();
            parentDTOtoStudent.setId(parentDTO.getId());
            parentDTOtoStudent.setFirstName(parentDTO.getFirstName());
            parentDTOtoStudent.setLastName(parentDTO.getLastName());

            //Send the request to the student service. through StudentCallerController
            studentDTOtoSend = studentCallerService.createStudent(requestCreateStudentDTO.getStudent());
            studentDTOtoSend.setParent(parentDTOtoStudent);

            if(studentDTOtoSend.getFirstName() != null){
                studentCreated = true;
            }

            //If everything was created successfully
            if(parentCreated && studentCreated){

                //insert everything to the return response
                returnCreateStudentDTO.setStudent(studentDTOtoSend);
                returnCreateStudentDTO.setParent(parentDTO);

                returnCreateStudentDTO.setOperationStatus(OperationStatusEnum.SUCCESS);
                returnCreateStudentDTO.setReason("Student was successfully created!");

                return returnCreateStudentDTO;
            }

            //If not
            returnCreateStudentDTO.setOperationStatus(OperationStatusEnum.FAILURE);
            returnCreateStudentDTO.setReason("Student was not created!");

            return returnCreateStudentDTO;
        }catch(Exception e){//If there is some error, revoke everything

            System.out.println(e);

            returnCreateStudentDTO.setOperationStatus(OperationStatusEnum.ERROR);
            returnCreateStudentDTO.setReason(e.getMessage());

            //If parent was create, remove it from the service
            if(parentCreated && parentDTO.getId() != null){
                parentCallerService.deleteParent(parentDTO);

                returnCreateStudentDTO.setOperationStatus(OperationStatusEnum.ERROR);
                returnCreateStudentDTO.setReason("There was an error processing the creation of the student!");
            }

            //If parent was create, remove it from the service
            if(studentCreated && studentDTOtoSend.getId() != null){
                parentCallerService.deleteParent(parentDTO);

                returnCreateStudentDTO.setOperationStatus(OperationStatusEnum.ERROR);
                returnCreateStudentDTO.setReason("There was an error processing the creation of the student!");
            }

        }

        return returnCreateStudentDTO;
    }

    //Function that calls both microservices and tries to
    public ClubStudentDTO associateStudentClub(Long studentId, Long clubId) {

        //Create an object to return to controller
        ClubStudentDTO clubStudentDTO = new ClubStudentDTO();

        //Send request to student micro service


        //Send request to club micro service




        return clubStudentDTO;
    }

    public ClubTownDTO associateClubTown(Long clubId, Long townId){
        ClubTownDTO clubTownDTO = new ClubTownDTO();

        ReturnClubTownDTO clubResponse = new ReturnClubTownDTO();
        ReturnClubTownDTO townResponse = new ReturnClubTownDTO();

        try{
            clubResponse = clubCallerService.associateClubTown(clubId, townId);
        }catch (Exception e){//Error on club
            System.out.println(e);

            clubTownDTO.setOperationStatus(OperationStatusEnum.ERROR);
            clubTownDTO.setReason("There was an error processing the association of the club!");

            return clubTownDTO;
        }

        try{
            townResponse = townCallerService.associateClubTown(clubId, townId);
        }catch (Exception e){//Error on town
            System.out.println(e);

            //Remove the previous one
            ReturnClubTownDTO returnError = clubCallerService.desassociateClubTown(clubId, townId);

            clubTownDTO.setOperationStatus(OperationStatusEnum.ERROR);
            clubTownDTO.setReason("There was an error processing the association of the club!\n" + returnError.getReason());

            return clubTownDTO;
        }



        clubTownDTO.setOperationStatus(OperationStatusEnum.SUCCESS);
        clubTownDTO.setReason("Club and Town was associated successfully!");
        clubTownDTO.setTownDTO(townResponse.getTownDTO());
        clubTownDTO.setClubDTO(clubResponse.getClubDTO());

        return clubTownDTO;
    }
}
