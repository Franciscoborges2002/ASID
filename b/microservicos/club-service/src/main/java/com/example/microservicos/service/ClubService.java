package com.example.microservicos.service;

import com.example.microservicos.dto.ClubDTO;
import com.example.microservicos.dto.clubStudentDTO;
import com.example.microservicos.extern.StudentCallerController;
import com.example.microservicos.model.Club;
import com.example.microservicos.model.Student;
import com.example.microservicos.repository.ClubRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;

    private final ModelMapper mapper;

    private final StudentCallerController studentCallerController;

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
    public clubStudentDTO attachStudentToClub(Long clubId, Long studentId) throws JsonProcessingException {
        //Create var to return
        clubStudentDTO toRespond = new clubStudentDTO();

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
        Student student = studentCallerController.findStudentById(studentId);

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


}
