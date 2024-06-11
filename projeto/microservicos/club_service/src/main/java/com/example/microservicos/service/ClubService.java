package com.example.microservicos.service;

import com.example.microservicos.dto.*;
import com.example.microservicos.extern.StudentCallerService;
import com.example.microservicos.extern.TownCallerService;
import com.example.microservicos.model.Club;
import com.example.microservicos.model.Student;
import com.example.microservicos.model.Town;
import com.example.microservicos.model.enums.OperationStatusEnum;
import com.example.microservicos.repository.ClubRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;

    private final ModelMapper mapper;

    private final StudentCallerService studentCallerController;

    private final TownCallerService townCallerService;

    public List<ClubDTO> getAllClubs() {
        return clubRepository.
                findAll().
                stream().
                map(this::mapToClubDTO).
                toList();
    }

    public Optional<ClubDTO> getClubById(Long clubId) {
        return clubRepository.
                findById(clubId).
                map(this::mapToClubDTO);
    }

    public Club addClub(ClubDTO clubDTO) {
        Club club = mapper.map(clubDTO, Club.class);

        clubRepository.save(club);

        return club;
    }

    //Added this method to attach a student to a club
    public ClubStudentDTO attachStudentToClub(Long clubId, Long studentId) throws JsonProcessingException {
        //Create var to return
        ClubStudentDTO toRespond = new ClubStudentDTO();

        //Verify if the club exists
        Optional<Club> optClub = clubRepository.findById(clubId);

        //If club doesn't exist
        if(optClub.isEmpty()){
            toRespond.setSuccess(false);
            toRespond.setMessage("Club doesnt exist!");
            return toRespond;
        }

        Club club = optClub.get();

        //Verify if the student exists with an id, send request to his service
        StudentDTO studentDTO = studentCallerController.findStudentById(studentId);

        Student student = mapToStudent(studentDTO);

        //Verify if already has the student in the club repo
        if(club.getStudents().contains(student)){
            toRespond.setSuccess(false);
            toRespond.setMessage("Student already belongs to this club!");
            return toRespond;
        }

        // Proceed with attaching the student to the club
        // Assuming you have a method to add students to the club
        club.getStudents().add(student);//getStudents().add(student);

        // Save the club after adding the student
        clubRepository.save(club);

        toRespond.setSuccess(true);
        toRespond.setMessage("Student added to the club!");
        return toRespond;
    }

    @Transactional
    public void deleteClubById(Long clubId) {
        /*Optional<Club> optionalClub = clubRepository.findById(clubId);


        if (optionalClub.isPresent()){
            Club club = optionalClub.get();
            Optional<List<Student>> studentsOptional = studentRepository.findByClubs(club);
            for (Student student : studentsOptional.get()){
                student.removeClub(club);
            }
        }

        clubRepository.deleteById(clubId);*/
    }


    private ClubDTO mapToClubDTO(Club club) {
        return mapper.map(club, ClubDTO.class);
    }

    private Student mapToStudent(StudentDTO studentDTO) {
        return mapper.map(studentDTO, Student.class);
    }

    public ReturnClubTownDTO attachTowntoClub(Long clubId, Long townId){
        ReturnClubTownDTO returnClubTownDTO = new ReturnClubTownDTO();

        Club club = new Club();
        TownDTO townVerification = new TownDTO();

        boolean clubExists = false;
        boolean townExists = false;

        //Try get the club
        try{
            Optional<Club> optClub = clubRepository.findById(clubId);

            //If club exists
            if(!optClub.isEmpty()){
                club = optClub.get();
                clubExists = true;
            }

        }catch(Exception e){
            System.out.println(e);
            returnClubTownDTO.setOperationStatus(OperationStatusEnum.ERROR);
            returnClubTownDTO.setReason("Cant find Club!");

            return returnClubTownDTO;
        }

        //Verify if town exists
        try{
            townVerification = townCallerService.findTownById(townId);

            //If town exists
            if(townVerification.getId() != null){
                townExists = true;
            }

        }catch(Exception e){
            System.out.println(e);
            returnClubTownDTO.setOperationStatus(OperationStatusEnum.ERROR);
            returnClubTownDTO.setReason("Town doesnt exist!");

            return returnClubTownDTO;
        }

        try{
            Town townAdd = new Town();
            townAdd.setId(townId);
            townAdd.setClub(club);

            club.setTown(townAdd);

            clubRepository.save(club);

        }catch(Exception e){
            System.out.println(e);
            returnClubTownDTO.setOperationStatus(OperationStatusEnum.FAILURE);
            returnClubTownDTO.setReason("Cant associate club with town. Club service!");

            return returnClubTownDTO;
        }

        returnClubTownDTO.setOperationStatus(OperationStatusEnum.SUCCESS);
        returnClubTownDTO.setReason("Club and Town associated!");
        returnClubTownDTO.setClubDTO(mapToClubDTO(club));
        returnClubTownDTO.setTownDTO(townVerification);

        return returnClubTownDTO;
    }

    public ReturnClubTownDTO dettachTowntoClub(Long clubId, Long townId){
        ReturnClubTownDTO returnClubTownDTO = new ReturnClubTownDTO();

        Club club = new Club();
        TownDTO townVerification = new TownDTO();

        boolean clubExists = false;
        boolean townExists = false;

        //Try get the club
        try{
            Optional<Club> optClub = clubRepository.findById(clubId);

            //If club exists
            if(!optClub.isEmpty()){
                club = optClub.get();
                clubExists = true;
            }

        }catch(Exception e){
            System.out.println(e);
            returnClubTownDTO.setOperationStatus(OperationStatusEnum.ERROR);
            returnClubTownDTO.setReason("Cant find Club!");

            return returnClubTownDTO;
        }

        //Verify if town exists
        try{
            townVerification = townCallerService.findTownById(townId);

            //If town exists
            if(townVerification.getId() != null){
                townExists = true;
            }

        }catch(Exception e){
            System.out.println(e);
            returnClubTownDTO.setOperationStatus(OperationStatusEnum.ERROR);
            returnClubTownDTO.setReason("Town doesnt exist!");

            return returnClubTownDTO;
        }

        try{
            club.setTown(null);

            System.out.println(club.getTown());

            clubRepository.save(club);

        }catch(Exception e){
            System.out.println(e);
            returnClubTownDTO.setOperationStatus(OperationStatusEnum.FAILURE);
            returnClubTownDTO.setReason("Cant disassociate club with town. Club service!");

            return returnClubTownDTO;
        }

        returnClubTownDTO.setOperationStatus(OperationStatusEnum.SUCCESS);
        returnClubTownDTO.setReason("Club and Town disassociated!");
        returnClubTownDTO.setClubDTO(mapToClubDTO(club));
        returnClubTownDTO.setTownDTO(townVerification);

        return returnClubTownDTO;
    }

    //deattachTowntoClub
}
