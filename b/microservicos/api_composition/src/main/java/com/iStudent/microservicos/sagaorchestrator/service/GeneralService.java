package com.iStudent.microservicos.sagaorchestrator.service;

import com.iStudent.microservicos.sagaorchestrator.dto.ClubStudentDTO;
import com.iStudent.microservicos.sagaorchestrator.dto.RequestCreateStudentDTO;
import com.iStudent.microservicos.sagaorchestrator.extern.StudentCallerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GeneralService {

    private final StudentCallerService studentCallerService;

    //Function to create a student
    public void createStudent(RequestCreateStudentDTO requestCreateStudentDTO){
        boolean studentCreated = false;
        boolean parentCreated = false;

        try{
            //Send the request to the student service. through StudentCallerController
            studentCallerService.createStudent(requestCreateStudentDTO.getStudent());
            studentCreated = true;


        }catch(Exception e){//If there is some error, revoke everything

            //if(studentCreated){//If student was create, remove it from the service

            //}

        }


    }

    //Function that calls both microservices and tries to
    public ClubStudentDTO associateStudentClub(Long studentId, Long clubId) {

        //Create an object to return to controller
        ClubStudentDTO clubStudentDTO = new ClubStudentDTO();

        //Send request to student micro service


        //Send request to club micro service




        return clubStudentDTO;
    }
}
